package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.dto.response.paypal.CatalogProductListResponse;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;
import com.accounting.einvoices.service.PaypalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paypal")
public class PaypalController {

    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/create/products")
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody CatalogProductRequest request) {
        CatalogProductResponse response = paypalService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Catalog product is successfully created.")
                .data(response)
                .build());
    }

    @GetMapping("/list/products")
    public ResponseEntity<ResponseWrapper> getProducts() {
        CatalogProductListResponse response = paypalService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Catalog product list is successfully retrieved.")
                .data(response)
                .build());
    }
}
