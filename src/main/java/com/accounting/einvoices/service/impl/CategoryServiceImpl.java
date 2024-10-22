package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CategoryDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.entity.Category;
import com.accounting.einvoices.exception.CategoryAlreadyExistsException;
import com.accounting.einvoices.exception.CategoryNotFoundException;
import com.accounting.einvoices.repository.CategoryRepository;
import com.accounting.einvoices.service.CategoryService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CompanyService companyService;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CompanyService companyService, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.companyService = companyService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAllByCompanyId(getLoggedInCompany().getId()).stream()
                .map(category -> mapperUtil.convert(category, new CategoryDTO())).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByDescription(String desc) {
        Category found = categoryRepository.findByDescriptionIgnoreCase(desc).orElseThrow(() -> new CategoryNotFoundException("Category not found."));
        return mapperUtil.convert(found, new CategoryDTO());
    }

    @Override
    public CategoryDTO save(CategoryDTO category) {
        Optional<Category> found = categoryRepository.findByDescriptionIgnoreCase(category.getDescription());
        if (found.isPresent()) throw new CategoryAlreadyExistsException("Category already exists.");
        category.setCompany(getLoggedInCompany());
        Category saved = categoryRepository.save(mapperUtil.convert(category, new Category()));
        return mapperUtil.convert(saved, new CategoryDTO());
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO category) {
        Category found = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found."));
        Category converted = mapperUtil.convert(category, new Category());
        converted.setId(id);
        converted.setCompany(found.getCompany());
        Category saved = categoryRepository.save(converted);
        return mapperUtil.convert(saved, new CategoryDTO());
    }

    @Override
    public void delete(Long id) {
        Category found = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found."));
        found.setIsDeleted(true);
        categoryRepository.save(found);
    }

    private CompanyDTO getLoggedInCompany(){
        return companyService.getByLoggedInUser();
    }
}
