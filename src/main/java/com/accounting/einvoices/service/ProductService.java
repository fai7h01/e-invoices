package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findByName(String name);

    ProductDTO save(ProductDTO product);

    ProductDTO update(Long id, ProductDTO product);

    void delete(Long id);

    void deleteAllByCategory(Long id);

    List<ProductDTO> findAllByCategoryId(Long id);

}
