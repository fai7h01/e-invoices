package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.exception.product.ProductLowLimitAlertException;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;

    public InvoiceController(InvoiceService invoiceService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<ResponseWrapper> createInvoice(@RequestBody InvoiceDTO invoice) {
//        InvoiceDTO saved = invoiceService.save(invoice);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(ResponseWrapper.builder()
//                .code(HttpStatus.CREATED.value())
//                .success(true)
//                .message("Invoice is successfully created.")
//                .data(saved).build());
//    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createInvoice(@RequestPart InvoiceDTO invoice, @RequestPart(required = false) MultipartFile file) {

        InvoiceDTO saved = file != null ? invoiceService.save(invoice, file) : invoiceService.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseWrapper.builder()
                        .code(HttpStatus.CREATED.value())
                        .success(true)
                        .message("Invoice is successfully created.")
                        .data(saved).build());
    }


    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.findAllByLoggedInUser();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Invoice list is successfully retrieved.")
                .data(invoices).build());
    }

    @GetMapping("/generate")
    public ResponseEntity<ResponseWrapper> generateInvoice() {
        InvoiceDTO generated = invoiceService.generateInvoice();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Invoice is successfully generated.")
                .data(generated).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoice){
        InvoiceDTO updatedInvoice = invoiceService.update(id, invoice);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Invoice is successfully updated.")
                .data(updatedInvoice).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteInvoice(@PathVariable Long id){
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add/product/{invoiceId}")
    public ResponseEntity<ResponseWrapper> addInvoiceProductToInvoice(@PathVariable("invoiceId") Long id,
                                                                      @RequestBody InvoiceProductDTO invoiceProduct){
        InvoiceProductDTO added = invoiceProductService.save(id, invoiceProduct);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Product is successfully added to invoice.")
                .data(added).build());
    }


    @GetMapping("/product/list/{invoiceId}")
    public ResponseEntity<ResponseWrapper> getInvoiceProductsByInvoice(@PathVariable("invoiceId") Long id) {
        List<InvoiceProductDTO> invoiceProducts = invoiceProductService.findAllByInvoiceId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Invoice products is successfully retrieved.")
                .data(invoiceProducts).build());
    }


    @DeleteMapping("/remove/product/{id}")
    public ResponseEntity<ResponseWrapper> removeInvoiceProductFromInvoice(@PathVariable Long id){
        invoiceProductService.delete(id);// find by invoice id and delete by id
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity<ResponseWrapper> approveInvoice(@PathVariable Long id){
        invoiceService.approve(id);
        try {
            invoiceProductService.lowQuantityAlert(id);
        } catch (ProductLowLimitAlertException e) {
            String message = e.getMessage();
            return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                    .success(true)
                    .message("Invoice is successfully approved.")
                    .alert(message).build());
        }
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Invoice is successfully approved.").build());
    }
}
