package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.PaypalProductClient;
import com.accounting.einvoices.client.PaypalSubscriptionClient;
import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.response.paypal.*;
import com.accounting.einvoices.service.PaypalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {

    private final PaypalProductClient paypalProductClient;
    private final PaypalSubscriptionClient paypalSubscriptionClient;


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

    @Override
    public PlanResponse createPlan(PlanRequest request) {
        return paypalSubscriptionClient.createPlan(request);
    }

    @Override
    public PlanListResponse getPlans() {
        return paypalSubscriptionClient.getPlans();
    }
}
