package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    List<ProductDTO> findAllByCurrency(String code);

    List<ProductDTO> findAllByCreatedDateBetweenMonths(int year, int startMonth, int endMonth, String currency);

    ProductDTO findByName(String name);

    ProductDTO save(ProductDTO product);

    ProductDTO update(Long id, ProductDTO product);

    void delete(Long id);

    void deleteAllByCategory(Long id);

    List<ProductDTO> findAllByCategoryId(Long id);

    boolean checkIfProductExists(String name);
}
