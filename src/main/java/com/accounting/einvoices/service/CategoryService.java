package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    List<CategoryDTO> findAllByProductCount();

    List<CategoryDTO> findCategoriesWithProductsByCurrency(String currency);

    CategoryDTO findByDescription(String desc);

    CategoryDTO save(CategoryDTO category);

    CategoryDTO update(Long id, CategoryDTO category);

    void delete(Long id);

    CategoryDTO increaseProductNumber(CategoryDTO category);

    CategoryDTO decreaseProductNumber(CategoryDTO category);

    boolean checkIfCategoryExists(String desc);

}
