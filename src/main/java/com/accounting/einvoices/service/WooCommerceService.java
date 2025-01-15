package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.WooCommerceCredentialsDTO;

public interface WooCommerceService {

    WooCommerceCredentialsDTO saveCredentials(WooCommerceCredentialsDTO dto);

    WooCommerceCredentialsDTO findByUsername(String username);

}
