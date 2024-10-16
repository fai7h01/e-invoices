package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CategoryDTO;
import com.accounting.einvoices.repository.CategoryRepository;
import com.accounting.einvoices.service.CategoryService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return List.of();
    }

    @Override
    public CategoryDTO create(CategoryDTO category) {
        return null;
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
