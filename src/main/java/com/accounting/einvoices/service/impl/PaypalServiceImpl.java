package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.PaypalProductClient;
import com.accounting.einvoices.client.PaypalPlanClient;
import com.accounting.einvoices.client.PaypalSubscriptionClient;
import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.request.paypal.pricing.PlanPricingRequest;
import com.accounting.einvoices.dto.request.paypal.subscription.SubscriptionRequest;
import com.accounting.einvoices.dto.response.paypal.*;
import com.accounting.einvoices.service.PaypalService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {

    private final PaypalProductClient paypalProductClient;
    private final PaypalPlanClient paypalPlanClient;
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
    public CatalogProductDetailsResponse getProductDetails(String id) {
        return paypalProductClient.getProductDetails(id);
    }

    @Override
    public PlanResponse createPlan(PlanRequest request) {
        return paypalPlanClient.createPlan(request);
    }

    @Override
    public PlanListResponse getPlans() {
        return paypalPlanClient.getPlans();
    }

    @Override
    public PlanDetailsResponse getPlanDetails(String id) {
        return paypalPlanClient.getPlanDetails(id);
    }

    @Override
    public void updatePlan(String id, JsonPatch request) {
        paypalPlanClient.updatePlan(id, request);
    }

    @Override
    public void activatePlan(String id, String any) {
        paypalPlanClient.activatePlan(id, any);
    }

    @Override
    public void deactivatePlan(String id, String any) {
        paypalPlanClient.deactivatePlan(id, any);
    }

    @Override
    public void updatePlanPricing(String id, PlanPricingRequest request) {
        paypalPlanClient.updatePlanPricing(id, request);
    }

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        return paypalSubscriptionClient.createSubscription(request);
    }

    @Override
    public SubscriptionDetailsResponse getSubscriptionDetails(String id) {
        return paypalSubscriptionClient.getSubscriptionDetails(id);
    }
}
