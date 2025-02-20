package com.accounting.einvoices.service;

import org.springframework.mail.SimpleMailMessage;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface EmailService {

    void sendEmailWithAttachment(String to, String subject, String text, byte [] pdfAttachment);

    CompletableFuture<byte[]> generatePdfFromHtmlTemplate(String templateName, Map<String, Object> model) throws IOException;

    void sendSupportEmail(String name, String email, String subject, String message);

    void sendSupportEmail(String name, String subject, String message);

    void sendGeneratedInvoicePdf(String invNo);

    void sendForgotPasswordEmail(String email);

    void sendVerificationEmail(String email);

    void sendConfirmPasswordResetEmail(String email);

    void sendEmail(SimpleMailMessage simpleMailMessage);
}
