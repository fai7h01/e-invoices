package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/mailing")
public class MailingController {

    private final EmailService emailService;

    public MailingController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email/{invNo}")
    public ResponseEntity<ResponseWrapper> sendEmail(@PathVariable("invNo") String invNo) {
        emailService.sendGeneratedInvoicePdf(invNo);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Email was sent successfully")
                .build());
    }

    @GetMapping("/send-email")
    public ResponseEntity<ResponseWrapper> sendSupportEmail(@RequestParam("name") String name,
                                                            @RequestParam(value = "email", required = false) String email,
                                                            @RequestParam("subject") String subject,
                                                            @RequestParam("message") String message) {
        if (email != null) {
            emailService.sendSupportEmail(name, email, subject, message);
        } else {
            emailService.sendSupportEmail(name, subject, message);
        }
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Email was sent successfully")
                .build());
    }
}
