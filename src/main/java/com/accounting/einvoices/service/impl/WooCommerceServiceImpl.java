package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.WooCommerceClient;
import com.accounting.einvoices.dto.*;
import com.accounting.einvoices.dto.response.woocommerce.WCProductResponse;
import com.accounting.einvoices.entity.WooCommerceCredentials;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.ProductStatus;
import com.accounting.einvoices.exception.category.CategoryAlreadyExistsException;
import com.accounting.einvoices.repository.WooCommerceRepository;
import com.accounting.einvoices.service.*;
import com.accounting.einvoices.util.BigDecimalUtil;
import com.accounting.einvoices.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class WooCommerceServiceImpl implements WooCommerceService {

    private final WooCommerceRepository wooCommerceRepository;
    private final WooCommerceClient wooCommerceClient;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;
    private final MapperUtil mapperUtil;

    public WooCommerceServiceImpl(WooCommerceRepository wooCommerceRepository, WooCommerceClient wooCommerceClient, ProductService productService,
                                  CategoryService categoryService, CompanyService companyService, MapperUtil mapperUtil) {
        this.wooCommerceRepository = wooCommerceRepository;
        this.wooCommerceClient = wooCommerceClient;
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public WooCommerceCredentialsDTO saveCredentials(WooCommerceCredentialsDTO dto) {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        if (dto.getCompanyDTO() == null) {
            dto.setCompanyDTO(loggedInCompany);
        }
        WooCommerceCredentials converted = mapperUtil.convert(dto, new WooCommerceCredentials());
        WooCommerceCredentials saved = wooCommerceRepository.save(converted);
        loggedInCompany.setHasWooCommerce(true);
        companyService.update(dto.getId(), loggedInCompany);
        return mapperUtil.convert(saved, new WooCommerceCredentialsDTO());
    }

    @Override
    public WooCommerceCredentialsDTO findByUsername(String company) {
        WooCommerceCredentials found = wooCommerceRepository.findByCompanyTitle(company).orElseThrow();
        return mapperUtil.convert(found, new WooCommerceCredentialsDTO());
    }

    @Override
    public List<WCProductResponse> fetchProducts() {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        WooCommerceCredentialsDTO foundCreds = findByUsername(loggedInCompany.getTitle());
        URI uri = URI.create(foundCreds.getBaseUrl());
        return wooCommerceClient.getProducts(uri, foundCreds.getConsumerKey(), foundCreds.getConsumerSecret());
    }

    @Override
    public void importProducts() {

        List<WCProductResponse> wcProducts = fetchProducts();

        for (WCProductResponse wcProduct : wcProducts) {
            createProductDto(wcProduct);
        }
    }

    @Override
    public void importProducts(List<WCProductResponse> wcProducts) {
        for (WCProductResponse wcProduct : wcProducts) {
            createProductDto(wcProduct);
        }
    }

    private void createProductDto(WCProductResponse wcProduct) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(wcProduct.getName());
        productDTO.setDescription(wcProduct.getDescription().substring(3, wcProduct.getDescription().length() - 5));
        productDTO.setQuantityInStock(wcProduct.getStockQuantity());
        productDTO.setLowLimitAlert(wcProduct.getLowStockAmount());
        productDTO.setPrice(BigDecimalUtil.format(new BigDecimal(wcProduct.getPrice())));
        productDTO.setCurrency(Currency.USD);
        productDTO.setCreatedAt(LocalDate.parse(wcProduct.getDateCreated().substring(0, 10)));
        productDTO.setStatus(ProductStatus.ACTIVE);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDescription(wcProduct.getCategories().get(0).getName());
        try{
            categoryService.save(categoryDTO);
        } catch (CategoryAlreadyExistsException e) {
            log.info(e.getMessage());
        }

        productDTO.setCategory(categoryDTO);
        productService.save(productDTO);
    }
}
