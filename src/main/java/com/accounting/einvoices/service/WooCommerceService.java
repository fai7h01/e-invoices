package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.WooCommerceCredentialsDTO;

import java.util.List;

public interface WooCommerceService {

    WooCommerceCredentialsDTO saveCredentials(WooCommerceCredentialsDTO dto);

    WooCommerceCredentialsDTO findByCompany(String title);

    WooCommerceCredentialsDTO findByCompanyId(Long id);

    List<ProductDTO> fetchProducts();

    void importAllProducts();

    void importProducts(List<ProductDTO> products);

}
