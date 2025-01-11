package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;
import com.accounting.einvoices.service.PaypalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaypalController {

    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/create/product")
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody CatalogProductRequest request) {
        CatalogProductResponse response = paypalService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CONTINUE.value())
                .success(true)
                .message("Catalog product is successfully created.")
                .data(response)
                .build());
    }
}
