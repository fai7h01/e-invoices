package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;

    public InvoiceController(InvoiceService invoiceService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createInvoice(@RequestBody InvoiceDTO invoice) {
        InvoiceDTO saved = invoiceService.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Invoice is successfully created.")
                .data(saved).build());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Invoice list is successfully retrieved.")
                .data(invoices).build());
    }
}
