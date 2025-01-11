package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.response.paypal.*;


public interface PaypalService {

    CatalogProductResponse createProduct(CatalogProductRequest request);

    CatalogProductListResponse getProducts();

    CatalogProductDetailsResponse getProductDetails(String productId);

    PlanResponse createPlan(PlanRequest request);

    PlanListResponse getPlans();

    PlanDetailsResponse getPlanDetails(String planId);


}
