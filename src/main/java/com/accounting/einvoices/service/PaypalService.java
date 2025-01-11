package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.response.paypal.CatalogProductResponse;

public interface PaypalService {

    CatalogProductResponse createProduct(CatalogProductRequest request);


}
