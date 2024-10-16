package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO create(CategoryDTO category);

    CategoryDTO update(Long id, CategoryDTO category);

    void delete(Long id);

}
