package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ProductService;
import com.accounting.einvoices.service.UserService;
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

    private final UserService userService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;
    private final InvoiceService invoiceService;


    public AssistantController(InvoiceService invoiceService, InvoiceProductService invoiceProductService, ProductService productService, UserService userService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.productService = productService;
        this.userService = userService;
    }

    //users
    @GetMapping("/user/list/all")
    public ResponseEntity<ResponseWrapper> getAllUsers() {
        List<UserDTO> users = userService.findAllByIngested(false);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Non-ingested User list is successfully retrieved.")
                .data(users).build());
    }


    //products
    @GetMapping("/product/list/all")
    public ResponseEntity<ResponseWrapper> getAllProducts() {
        List<ProductDTO> products = productService.findAllByIngested(false);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Non-ingested Product list is successfully retrieved.")
                .data(products).build());
    }

    //invoice products
    @GetMapping("/invoiceProduct/list/all")
    public ResponseEntity<ResponseWrapper> getAllInvoiceProducts() {
        List<InvoiceProductDTO> invoiceProducts = invoiceProductService.findAllByIngested(false);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Non-ingested InvoiceProduct list is successfully retrieved.")
                .data(invoiceProducts).build());
    }

    //invoices
    @GetMapping("/invoice/list/all")
    public ResponseEntity<ResponseWrapper> getAllInvoicesForAssistant() {
        List<InvoiceDTO> invoices = invoiceService.findAllByIngested(false);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Non-ingested Invoice list is successfully retrieved.")
                .data(invoices).build());
    }


    @PostMapping("/invoice/ingest")
    public ResponseEntity<ResponseWrapper> ingestInvoices() {
        invoiceService.setIngested(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Invoices are ingested successfully.").build());
    }

}