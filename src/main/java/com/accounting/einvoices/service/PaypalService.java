package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.request.paypal.PlanUpdateRequest;
import com.accounting.einvoices.dto.response.paypal.*;


public interface PaypalService {

    CatalogProductResponse createProduct(CatalogProductRequest request);

    CatalogProductListResponse getProducts();

    CatalogProductDetailsResponse getProductDetails(String id);

    PlanResponse createPlan(PlanRequest request);

    PlanListResponse getPlans();

    PlanDetailsResponse getPlanDetails(String id);

    void updatePlan(String planId, PlanUpdateRequest request);

    void activatePlan(String id, String any);


}
