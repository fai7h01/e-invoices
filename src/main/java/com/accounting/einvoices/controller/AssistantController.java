package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.ai_analysis.ClientAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.SalesAnalysisDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.AIReportingService;
import com.accounting.einvoices.service.EmailService;
import com.accounting.einvoices.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/v1/assistant")
public class AssistantController {

    private final InvoiceService invoiceService;
    private final EmailService emailService;
    private final AIReportingService aiReportingService;

    public AssistantController(InvoiceService invoiceService, EmailService emailService, AIReportingService aiReportingService) {
        this.invoiceService = invoiceService;
        this.emailService = emailService;
        this.aiReportingService = aiReportingService;
    }

    @GetMapping("/client-analysis")
    public ResponseEntity<ResponseWrapper> getInvoiceAnalysis() {
        ClientAnalysisDTO clientAnalysis = aiReportingService.getClientAnalysis();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Client Analysis is successfully retrieved.")
                .data(clientAnalysis)
                .build());
    }


    @GetMapping("/invoice-analysis/{year}/{startMonth}/{endMonth}")
    public ResponseEntity<ResponseWrapper> getInvoiceAnalysis(@PathVariable("year") String year,
                                                              @PathVariable("startMonth") String startMonth,
                                                              @PathVariable("endMonth") String endMonth) {
        InvoiceAnalysisDTO invoiceAnalysis =
                aiReportingService.getInvoiceAnalysis(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth));
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Invoice Analysis is successfully retrieved.")
                .data(invoiceAnalysis)
                .build());
    }


    @GetMapping("/sales-analysis/{year}/{startMonth}/{endMonth}")
    public ResponseEntity<ResponseWrapper> getSalesAnalysis(@PathVariable("year") String year,
                                                            @PathVariable("startMonth") String startMonth,
                                                            @PathVariable("endMonth") String endMonth) {
        SalesAnalysisDTO salesAnalysis =
                aiReportingService.getSalesAnalysis(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth));
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Sales Analysis is successfully retrieved.")
                .data(salesAnalysis)
                .build());
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
