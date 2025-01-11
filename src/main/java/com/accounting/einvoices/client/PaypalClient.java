package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "paypalClient", url = "https://api-m.sandbox.paypal.com")
public interface PaypalClient {

    @PostMapping("/v1/catalogs/products")
    CatalogProductResponse createProduct(@RequestBody CatalogProductRequest productRequest);
}
