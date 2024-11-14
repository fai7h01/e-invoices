package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/assistant")
public class AssistantController {

    private final InvoiceService invoiceService;

    public AssistantController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @GetMapping("/invoice/list/{companyTitle}")
    public ResponseEntity<ResponseWrapper> getInvoicesByLoggedInCompany(@PathVariable("companyTitle") String company) {
        List<InvoiceDTO> invoices = invoiceService.findAllByCompanyTitle(company);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Invoice list is successfully retrieved.")
                .data(invoices).build());
    }

    @PostMapping("/invoice/approve/{invNo}/{companyTitle}")
    public ResponseEntity<ResponseWrapper> approveInvoiceByInvNoAndCompanyTitle(@PathVariable("invNo") String invNo,
                                                                                @PathVariable("companyTitle") String company) {
        InvoiceDTO invoice = invoiceService.approve(invNo, company);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseWrapper.builder()
                .code(HttpStatus.ACCEPTED.value())
                .success(true)
                .message("Invoice is successfully approved.")
                .data(invoice)
                .build());
    }


}
