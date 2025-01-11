package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.PaypalProductClient;
import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.response.paypal.CatalogProductDetailsResponse;
import com.accounting.einvoices.dto.response.paypal.CatalogProductListResponse;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;
import com.accounting.einvoices.service.PaypalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {

    private final PaypalProductClient paypalProductClient;


    @Override
    public CatalogProductResponse createProduct(CatalogProductRequest request) {
        return paypalProductClient.createProduct(request);
    }

    @Override
    public CatalogProductListResponse getProducts() {
        return paypalProductClient.getProductList();
    }

    @Override
    public CatalogProductDetailsResponse getProductDetails(String productId) {
        return paypalProductClient.getProductDetails(productId);
    }
}
