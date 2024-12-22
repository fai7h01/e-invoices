package com.accounting.einvoices.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface EmailService {

    CompletableFuture<String> sendEmailWithAttachment(String to, String subject, String text, byte [] pdfAttachment);

    CompletableFuture<byte[]> generatePdfFromHtmlTemplate(String templateName, Map<String, Object> model) throws IOException;

}
