package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.PaypalClient;
import com.accounting.einvoices.config.PaypalConfig;
import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.response.paypal.CatalogProductListResponse;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;
import com.accounting.einvoices.service.PaypalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaypalServiceImpl implements PaypalService {

    private final PaypalConfig paypalConfig;
    private final PaypalClient paypalClient;

    public PaypalServiceImpl(PaypalConfig paypalConfig, PaypalClient paypalClient) {
        this.paypalConfig = paypalConfig;
        this.paypalClient = paypalClient;
    }


    @Override
    public CatalogProductResponse createProduct(CatalogProductRequest request) {
        return paypalClient.createProduct(request);
    }

    @Override
    public CatalogProductListResponse getProducts() {
        return paypalClient.getProductList();
    }
}
