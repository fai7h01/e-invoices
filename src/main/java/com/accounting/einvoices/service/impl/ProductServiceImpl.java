package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CategoryDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.entity.Product;
import com.accounting.einvoices.exception.ProductAlreadyExistsException;
import com.accounting.einvoices.exception.ProductNotFoundException;
import com.accounting.einvoices.repository.ProductRepository;
import com.accounting.einvoices.service.CategoryService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.ProductService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final CompanyService companyService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, CompanyService companyService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.companyService = companyService;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAllByCategoryCompanyId(getLoggedInCompany().getId()).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO create(ProductDTO product) {
        Optional<Product> foundProduct = productRepository.findByNameIgnoreCase(product.getName());
        if (foundProduct.isPresent()) throw new ProductAlreadyExistsException("Product already exists.");
        CategoryDTO foundCategory = categoryService.findByDescription(product.getCategory().getDescription());
        product.setCategory(foundCategory);
        Product saved = productRepository.save(mapperUtil.convert(product, new Product()));
        return mapperUtil.convert(saved, new ProductDTO());
    }

    @Override
    public ProductDTO update(Long id, ProductDTO product) {
        Product found = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found."));
        Product converted = mapperUtil.convert(product, new Product());
        converted.setId(found.getId());
        converted.setCategory(found.getCategory());
        Product saved = productRepository.save(converted);
        return mapperUtil.convert(saved, new ProductDTO());
    }

    @Override
    public void delete(Long id) {
        Product found = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found."));
        found.setIsDeleted(true);
        productRepository.save(found);
    }

    private CompanyDTO getLoggedInCompany(){
        return companyService.getByLoggedInUser();
    }
}
