package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.exception.product.ProductLowLimitAlertException;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final StorageService storageService;

    public InvoiceController(InvoiceService invoiceService, InvoiceProductService invoiceProductService, StorageService storageService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.storageService = storageService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createInvoice(@RequestBody @Valid InvoiceDTO invoice) {
        InvoiceDTO saved = invoiceService.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Invoice is successfully created.")
                .data(saved).build());
    }

    @PostMapping("/attachment-upload")
    public ResponseEntity<ResponseWrapper> createInvoice(@RequestParam("invNo") String invNo, @RequestParam("file") MultipartFile file) {
        invoiceService.uploadInvoiceAttachment(invNo, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .success(true)
                        .message("Attachment is successfully upload for invoice " + invNo + ".")
                        .build());
    }

//    @GetMapping("/download")
//    public ResponseEntity<ResponseWrapper> downloadInvoiceAttachment(@RequestParam("fileName") String key) {
//        storageService.downloadFile(key);
//        key = key.substring(key.lastIndexOf("_") + 1);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
//                .body(ResponseWrapper.builder()
//                        .code(HttpStatus.OK.value())
//                        .success(true)
//                        .message("File is successfully downloaded.")
//                        .build());
//    }


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
    public ResponseEntity<ResponseWrapper> updateInvoice(@PathVariable Long id, @RequestBody @Valid InvoiceDTO invoice){
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
                                                                      @RequestBody @Valid InvoiceProductDTO invoiceProduct){
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
