package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

}
