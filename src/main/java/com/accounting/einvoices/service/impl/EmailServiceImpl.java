package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.ForgotPasswordToken;
import com.accounting.einvoices.entity.VerificationToken;
import com.accounting.einvoices.repository.ForgotPasswordTokenRepository;
import com.accounting.einvoices.repository.VerificationTokenRepository;
import com.accounting.einvoices.service.EmailService;
import com.accounting.einvoices.service.UserService;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${app.base-url}")
    private String BASE_URL;

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final UserService userService;
    private final ForgotPasswordTokenRepository forgotPasswordTokenRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public EmailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine, UserService userService,
                            ForgotPasswordTokenRepository forgotPasswordTokenRepository, VerificationTokenRepository verificationTokenRepository) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.userService = userService;
        this.forgotPasswordTokenRepository = forgotPasswordTokenRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Async("asyncTaskExecutor")
    @Override
    public CompletableFuture<String> sendEmailWithAttachment(String to, String subject, String text, byte[] pdfAttachment) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("invoice.pdf", new ByteArrayDataSource(pdfAttachment, "application/pdf"));
            emailSender.send(message);
            return CompletableFuture.completedFuture("Email sent successfully.");
        } catch (MessagingException e) {
            log.info("Email Service Exception: {}", e.getMessage());
            return CompletableFuture.completedFuture("Sending email failed!");
        }
    }

    @Async("asyncTaskExecutor")
    @Override
    public CompletableFuture<byte[]> generatePdfFromHtmlTemplate(String templateName, Map<String, Object> model) throws IOException {
        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process(templateName, context);
        return CompletableFuture.completedFuture(generatePdfFromHtml(html));
    }

    private byte[] generatePdfFromHtml(String html) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            HtmlConverter.convertToPdf(html, os);
            return os.toByteArray();
        }
    }


    @Async("asyncTaskExecutor")
    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        emailSender.send(simpleMailMessage);
    }


    @Override
    public void sendForgotPasswordEmail(String email) {
        SimpleMailMessage simpleMailMessage = createForgotPasswordEmail(email);
        this.sendEmail(simpleMailMessage);
    }

    @Override
    public void sendConfirmPasswordResetEmail(String email) {
        SimpleMailMessage simpleMailMessage = createConfirmPasswordResetEmail(email);
        this.sendEmail(simpleMailMessage);
    }

    @Override
    public void sendVerificationEmail(String email) {
        SimpleMailMessage simpleMailMessage = createVerificationEmail(email);
        this.sendEmail(simpleMailMessage);
    }

    private SimpleMailMessage createVerificationEmail(String email) {
        String fullname = findUserFullName(email);

        VerificationToken verificationToken = new VerificationToken(email);
        VerificationToken createdToken = verificationTokenRepository.save(verificationToken);

        String message = createVerificationMessage(email, fullname, createdToken.getToken(), createdToken.getExpiryDate());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Email Verification");
        mail.setText(message);

        return mail;
    }

    private String createVerificationMessage(String email, String fullname, String token, LocalDate expiryDate) {

        String companyName = "InvoiceHub";
        String link = BASE_URL + "/register/activate?email=" + email + "&token=" + token;
        String tokenExpiryDate = expiryDate.toString();
        String supportEmail = "invoicehub@gmail.com";
        String teamName = "Invoicehub Support";

        String messageDraft = """
                Dear %s,
                
                Thank you for registering with %s! We're excited to have you on board.
                
                To complete your registration and unlock all the features, please confirm your email address by clicking the link below:
                
                %s
                
                If you did not registered to our application, you can safely ignore this email.
                
                For security reasons, this confirmation link will expire at %s. If the link expired, you will need to request a new confirmation.
                
                If you have any questions or need further assistance, feel free to contact our support team at %s.
                
                Best Regards.
                The %s Team
                """;
        return String.format(messageDraft, fullname, companyName, link, tokenExpiryDate, supportEmail, teamName);

    }


    private SimpleMailMessage createConfirmPasswordResetEmail(String email) {
        String fullname = findUserFullName(email);

        String message = createConfirmPasswordMessage(fullname);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Password Reset Confirmation");
        mail.setText(message);

        return mail;
    }


    private SimpleMailMessage createForgotPasswordEmail(String email) {
        String fullname = findUserFullName(email);

        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(email);
        ForgotPasswordToken createdToken = forgotPasswordTokenRepository.save(forgotPasswordToken);

        String message = createForgotPasswordMessage(email, fullname, createdToken.getToken(), createdToken.getExpiryDate());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Forgot Password");
        mail.setText(message);

        return mail;
    }

    private String createForgotPasswordMessage(String email, String fullname, String token, LocalDate expiryDate) {

        String companyName = "InvoiceHub";
        String link = BASE_URL + "/password/new-password?email=" + email + "&token=" + token;
        String tokenExpiryDate = expiryDate.toString();
        String supportEmail = "invoicehub@gmail.com";
        String teamName = "Invoicehub Support";

        String messageDraft = """
                Dear %s,
                
                We received a request to reset your password for your account at %s. If you made this request, please click the link below to reset your password:
                
                %s
                
                If you did not request to reset your password, you can safely ignore this email. Your password will remain unchanged.
                
                For security reasons, this password reset link will expire at %s. If the link expired, you will need to request a new password reset.
                
                If you have any questions or need further assistance, feel free to contact our support team at %s.
                
                Best Regards.
                The %s Team
                """;
        return String.format(messageDraft, fullname, companyName, link, tokenExpiryDate, supportEmail, teamName);
    }

    private String createConfirmPasswordMessage(String fullname) {
        String supportEmail = "invoicehub@gmail.com";
        String teamName = "Invoicehub Support";
        String messageDraft = """
                Dear %s,
                
                Your password has been successfully updated!
                
                If you have any questions or need further assistance, feel free to contact our support team at %s.
                
                Best Regards.
                The %s Team
                """;
        return String.format(messageDraft, fullname, supportEmail, teamName);
    }

    private String findUserFullName(String email) {
        UserDTO dto = userService.findByUsername(email);
        return dto.getFirstName() + " " + dto.getLastName();
    }
}
