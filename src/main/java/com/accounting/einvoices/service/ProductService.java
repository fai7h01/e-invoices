package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    List<ProductDTO> findAllByIngested(boolean ingested);

    ProductDTO findByName(String name);

    ProductDTO save(ProductDTO product);

    ProductDTO update(Long id, ProductDTO product);

    void delete(Long id);

}
