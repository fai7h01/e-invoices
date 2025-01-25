package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.WooCommerceCredentialsDTO;
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

    @GetMapping("/credentials/{companyId}")
    public ResponseEntity<ResponseWrapper> findByCompany(@PathVariable("companyId") Long id) {
        WooCommerceCredentialsDTO found = wooCommerceService.findByCompanyId(id);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Woo-Commerce Credentials is retrieved")
                .data(found)
                .build());
    }


    @PostMapping("/save/credentials")
    public ResponseEntity<ResponseWrapper> saveCredentials(@RequestBody WooCommerceCredentialsDTO request) {
        boolean connection = wooCommerceService.checkIfConnected(request);
        if (connection) {
            WooCommerceCredentialsDTO saved = wooCommerceService.saveCredentials(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                    .code(HttpStatus.CREATED.value())
                    .success(true)
                    .message("Woo-Commerce Credentials is saved.")
                    .data(saved)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .success(false)
                    .message("Connection could not established.")
                    .build());
        }

    }

    @GetMapping("/list/products")
    public ResponseEntity<ResponseWrapper> getProducts() {
        List<ProductDTO> products = wooCommerceService.fetchProducts();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Product list is successfully retrieved.")
                .data(products)
                .build());
    }

    @PostMapping("/import/products")
    public ResponseEntity<ResponseWrapper> importProducts(@RequestBody() List<ProductDTO> products) {
        wooCommerceService.importProducts(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Products is successfully imported.")
                .build());
    }


    @PostMapping("/import/products/all")
    public ResponseEntity<ResponseWrapper> importAllProducts() {
        wooCommerceService.importAllProducts();
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Products is successfully imported.")
                .build());
    }
}
