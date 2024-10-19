package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO create(ProductDTO product);

    ProductDTO update(Long id, ProductDTO product);

    void delete(Long id);

}
