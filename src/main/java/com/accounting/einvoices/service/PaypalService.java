package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.request.paypal.pricing.PlanPricingRequest;
import com.accounting.einvoices.dto.request.paypal.subscription.SubscriptionRequest;
import com.accounting.einvoices.dto.response.paypal.*;
import com.github.fge.jsonpatch.JsonPatch;


public interface PaypalService {

    CatalogProductResponse createProduct(CatalogProductRequest request);

    CatalogProductListResponse getProducts();

    CatalogProductDetailsResponse getProductDetails(String id);

    PlanResponse createPlan(PlanRequest request);

    PlanListResponse getPlans();

    PlanDetailsResponse getPlanDetails(String id);

    void updatePlan(String planId, JsonPatch request);

    void activatePlan(String id, String any);

    void deactivatePlan(String id, String any);

    void updatePlanPricing(String id, PlanPricingRequest request);

    SubscriptionResponse createSubscription(SubscriptionRequest request);

    SubscriptionDetailsResponse getSubscriptionDetails(String id);
}
