package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.EmailService;
import com.accounting.einvoices.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/assistant")
public class AssistantController {

    private final InvoiceService invoiceService;
    private final EmailService emailService;

    public AssistantController(InvoiceService invoiceService, EmailService emailService) {
        this.invoiceService = invoiceService;
        this.emailService = emailService;
    }


    @PostMapping("/invoice/approve/{invNo}")
    public ResponseEntity<ResponseWrapper> approveInvoiceByInvNoAndCompanyTitle(@PathVariable("invNo") String invNo) {
        InvoiceDTO invoice = invoiceService.approve(invNo);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseWrapper.builder()
                .code(HttpStatus.ACCEPTED.value())
                .success(true)
                .message("Invoice is successfully approved.")
                .data(invoice)
                .build());
    }


    @GetMapping("/invoice/send/{invNo}")
    public ResponseEntity<ResponseWrapper> sendInvoicePdfViaMailToClient(@PathVariable("invNo") String invNo) {
        emailService.sendGeneratedInvoicePdf(invNo);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseWrapper.builder()
                .code(HttpStatus.ACCEPTED.value())
                .success(true)
                .message("Invoice is successfully sent to client!")
                .build());
    }


}
