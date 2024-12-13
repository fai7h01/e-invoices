package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CategoryDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.entity.Product;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.exception.ProductAlreadyExistsException;
import com.accounting.einvoices.exception.ProductCannotBeDeletedException;
import com.accounting.einvoices.exception.ProductNotFoundException;
import com.accounting.einvoices.repository.ProductRepository;
import com.accounting.einvoices.service.CategoryService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.ProductService;
import com.accounting.einvoices.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final CompanyService companyService;
    private final CategoryService categoryService;
    private final InvoiceProductService invoiceProductService;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, CompanyService companyService, CategoryService categoryService, InvoiceProductService invoiceProductService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.companyService = companyService;
        this.categoryService = categoryService;
        this.invoiceProductService = invoiceProductService;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAllByCategoryCompanyId(getLoggedInCompany().getId()).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByCreatedDate(int year, int month, String currency) {
        return productRepository.findAllByCreatedDate(year, month, Currency.valueOf(currency)).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .toList();
    }

    @Override
    public ProductDTO findByName(String name) {
        Product foundProduct = productRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFoundException("Product not found."));
        return mapperUtil.convert(foundProduct, new ProductDTO());
    }

    @Override
    public ProductDTO save(ProductDTO product) {
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
        //if there is invoiceProducts product cannot be deleted
        if (invoiceProductService.checkIfCanBeDeleted(id)) {
            throw new ProductCannotBeDeletedException("Product is already used in invoice(s) and cannot be deleted.");
        }
        found.setIsDeleted(true);
        productRepository.save(found);
    }

    @Override
    public void deleteAllByCategory(Long id) {
        List<Product> products = productRepository.findAllByCategoryId(id);
        if (!products.isEmpty()) {
            products.forEach(product -> {
                delete(product.getId());
            });
        }
      //  throw new ProductNotFoundException("Products not found to delete.")
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(Long id) {
        return productRepository.findAllByCategoryId(id).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .toList();
    }

    private CompanyDTO getLoggedInCompany(){
        return companyService.getByLoggedInUser();
    }
}
