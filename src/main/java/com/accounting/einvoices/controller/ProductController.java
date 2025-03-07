package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllProducts(){
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Product list is successfully retrieved.")
                .data(products).build());
    }

    @GetMapping("/list/{currency_code}")
    public ResponseEntity<ResponseWrapper> getProductsByCurrency(@PathVariable("currency_code") String currency){
        List<ProductDTO> products = productService.findAllByCurrency(currency);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Product list is successfully retrieved.")
                .data(products).build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO product){
        ProductDTO saved = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Product is successfully created.")
                .data(saved).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO product){
        ProductDTO updated = productService.update(id, product);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Product is successfully updated.")
                .data(updated).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
