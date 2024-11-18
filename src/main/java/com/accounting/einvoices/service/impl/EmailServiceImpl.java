package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.service.EmailService;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String text, byte[] pdfAttachment) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("invoice.pdf", new ByteArrayDataSource(pdfAttachment, "application/pdf"));
            emailSender.send(message);
        } catch (MessagingException e) {
            log.info("Email Service Exception: {}", e.getMessage());
        }

    }

    @Override
    public byte[] generatePdfFromHtmlTemplate(String templateName, Map<String, Object> model) throws IOException {
        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process(templateName, context);
        return generatePdfFromHtml(html);
    }

    private byte[] generatePdfFromHtml(String html) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            HtmlConverter.convertToPdf(html, os);
            return os.toByteArray();
        }
    }
}
