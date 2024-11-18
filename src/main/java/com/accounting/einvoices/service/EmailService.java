package com.accounting.einvoices.service;

import java.io.IOException;
import java.util.Map;

public interface EmailService {

    void sendEmailWithAttachment(String to, String subject, String text, byte [] pdfAttachment);

    byte[] generatePdfFromHtmlTemplate(String templateName, Map<String, Object> model) throws IOException;

}
