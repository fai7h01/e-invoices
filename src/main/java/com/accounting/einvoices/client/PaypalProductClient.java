package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.request.paypal.plan.CatalogProductRequest;
import com.accounting.einvoices.dto.response.paypal.CatalogProductDetailsResponse;
import com.accounting.einvoices.dto.response.paypal.CatalogProductListResponse;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paypalProductClient", url = "https://api-m.sandbox.paypal.com")
public interface PaypalProductClient {

    @PostMapping("/v1/catalogs/products")
    CatalogProductResponse createProduct(@RequestBody CatalogProductRequest productRequest);

    @GetMapping("/v1/catalogs/products")
    CatalogProductListResponse getProductList();

    @GetMapping("/v1/catalogs/products/{productId}")
    CatalogProductDetailsResponse getProductDetails(@PathVariable("productId") String productId);

}
