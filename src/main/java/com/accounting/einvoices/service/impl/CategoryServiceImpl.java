package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CategoryDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.entity.Category;
import com.accounting.einvoices.exception.category.CategoryAlreadyExistsException;
import com.accounting.einvoices.exception.category.CategoryCannotBeDeletedException;
import com.accounting.einvoices.exception.category.CategoryNotFoundException;
import com.accounting.einvoices.repository.CategoryRepository;
import com.accounting.einvoices.service.CategoryService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.ProductService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CompanyService companyService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CompanyService companyService,
                               @Lazy ProductService productService, InvoiceProductService invoiceProductService, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.companyService = companyService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAllByCompanyId(getLoggedInCompany().getId()).stream()
                .map(category -> mapperUtil.convert(category, new CategoryDTO())).toList();
    }

    @Override
    public List<CategoryDTO> findAllByProductCount() {
        return categoryRepository.findAllByProductCountGreater(getLoggedInCompany().getId()).stream()
                .map(category -> mapperUtil.convert(category, new CategoryDTO())).toList();
    }

    //find categories that have products based on currency

    @Override
    public CategoryDTO findByDescription(String desc) {
        Category found = categoryRepository.findByDescriptionIgnoreCaseAndCompany_Id(desc, getLoggedInCompany().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found."));
        return mapperUtil.convert(found, new CategoryDTO());
    }

    @Override
    public CategoryDTO save(CategoryDTO category) {
        Optional<Category> found = categoryRepository.findByDescriptionIgnoreCaseAndCompany_Id(category.getDescription(), getLoggedInCompany().getId());
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
        //find products by category and delete all of them/
        //check if category can be deleted
        if (checkIfCategoryCanBeDeleted(found.getId())) {
            productService.deleteAllByCategory(id);
            found.setIsDeleted(true);
            categoryRepository.save(found);
            return;
        }
        throw new CategoryCannotBeDeletedException("There is at least one product that is used in invoice!");
    }

    @Override
    public CategoryDTO increaseProductNumber(CategoryDTO category) {
        category.setProductCounter(category.getProductCounter() + 1);
        return update(category.getId(), category);
    }

    @Override
    public CategoryDTO decreaseProductNumber(CategoryDTO category) {
        category.setProductCounter(category.getProductCounter() - 1);
        return update(category.getId(), category);
    }

    @Override
    public boolean checkIfCategoryExists(String desc) {
        Optional<Category> foundCategory = categoryRepository.findByDescriptionIgnoreCaseAndCompany_Id(desc, getLoggedInCompany().getId());
        return foundCategory.isPresent();
    }

    private boolean checkIfCategoryCanBeDeleted(Long id) {
        List<ProductDTO> products = productService.findAllByCategoryId(id);
        if (!products.isEmpty()) {
            for (ProductDTO product : products) {
                List<InvoiceProductDTO> invoiceProducts = invoiceProductService.findAllByProductId(product.getId());
                return invoiceProducts.isEmpty();
            }
        }
        return true;
    }

    private CompanyDTO getLoggedInCompany(){
        return companyService.getByLoggedInUser();
    }
}
