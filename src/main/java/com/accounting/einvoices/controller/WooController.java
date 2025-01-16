package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.WooCommerceCredentialsDTO;
import com.accounting.einvoices.dto.response.woocommerce.WCProductResponse;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.WooCommerceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/woo-commerce")
public class WooController {

    private final WooCommerceService wooCommerceService;

    public WooController(WooCommerceService wooCommerceService) {
        this.wooCommerceService = wooCommerceService;
    }

    @PostMapping("/save/credentials")
    public ResponseEntity<ResponseWrapper> saveCredentials(@RequestBody WooCommerceCredentialsDTO request) {
        log.info("WOOCOMMERCE: {}", request);
        WooCommerceCredentialsDTO saved = wooCommerceService.saveCredentials(request);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Woo-Commerce Credentials is saved..")
                .data(saved)
                .build());
    }

    @GetMapping("/list/products")
    public ResponseEntity<ResponseWrapper> getProducts() {

        List<WCProductResponse> products = wooCommerceService.fetchProducts();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Product list is successfully retrieved.")
                .data(products)
                .build());
    }

    @PostMapping("/import/products")
    public ResponseEntity<ResponseWrapper> importProducts() {

        wooCommerceService.importProducts();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Products is successfully imported.")
                .build());
    }
}
