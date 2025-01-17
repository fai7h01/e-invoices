package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.WooCommerceCredentialsDTO;
import com.accounting.einvoices.dto.response.woocommerce.WCProductResponse;

import java.util.List;

public interface WooCommerceService {

    WooCommerceCredentialsDTO saveCredentials(WooCommerceCredentialsDTO dto);

    WooCommerceCredentialsDTO findByUsername(String username);

    List<WCProductResponse> fetchProducts();

    void importProducts();

}
