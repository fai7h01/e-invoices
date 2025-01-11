package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.response.paypal.CatalogProductListResponse;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;

import java.util.List;

public interface PaypalService {

    CatalogProductResponse createProduct(CatalogProductRequest request);

    CatalogProductListResponse getProducts();


}
