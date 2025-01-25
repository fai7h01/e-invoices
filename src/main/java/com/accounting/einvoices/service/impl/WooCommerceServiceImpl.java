package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.WooCommerceClient;
import com.accounting.einvoices.dto.*;
import com.accounting.einvoices.dto.response.woocommerce.WCProductResponse;
import com.accounting.einvoices.entity.WooCommerceCredentials;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.ProductStatus;
import com.accounting.einvoices.exception.WooCommerceCredentialsAlreadyExistsException;
import com.accounting.einvoices.repository.WooCommerceRepository;
import com.accounting.einvoices.service.*;
import com.accounting.einvoices.util.BigDecimalUtil;
import com.accounting.einvoices.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public boolean checkIfConnected(WooCommerceCredentialsDTO request) {
        try {
            URI uri = URI.create(request.getBaseUrl());
            List<WCProductResponse> response = wooCommerceClient.getProducts(uri, request.getConsumerKey(), request.getConsumerSecret());
            return response != null;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public WooCommerceCredentialsDTO saveCredentials(WooCommerceCredentialsDTO dto) {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        Optional<WooCommerceCredentials> found = wooCommerceRepository.findByCompanyTitle(loggedInCompany.getTitle());
        if (found.isPresent()) {
            throw new WooCommerceCredentialsAlreadyExistsException("Credentials already exists.");
        }

        if (dto.getCompanyDTO() == null) {
            loggedInCompany.setHasWooCommerce(true);
            CompanyDTO updated = companyService.update(loggedInCompany.getId(), loggedInCompany);
            dto.setCompanyDTO(updated);
        }
        WooCommerceCredentials converted = mapperUtil.convert(dto, new WooCommerceCredentials());
        WooCommerceCredentials saved = wooCommerceRepository.save(converted);
        return mapperUtil.convert(saved, new WooCommerceCredentialsDTO());
    }

    @Override
    public WooCommerceCredentialsDTO findByCompany(String title) {
        WooCommerceCredentials found = wooCommerceRepository.findByCompanyTitle(title).orElseThrow();
        return mapperUtil.convert(found, new WooCommerceCredentialsDTO());
    }

    @Override
    public WooCommerceCredentialsDTO findByCompanyId(Long id) {
        WooCommerceCredentials found = wooCommerceRepository.findByCompanyId(id)
                .orElseThrow(() -> new NotFoundException("Credentials not found"));
        return mapperUtil.convert(found, new WooCommerceCredentialsDTO());
    }


    @Override
    public List<ProductDTO> fetchProducts() {
        List<WCProductResponse> products = getProductsFromWooCommerce();
        return getConvertedProducts(products);
    }

    @Override
    public void importAllProducts() {
        List<ProductDTO> wcProducts = fetchProducts();
        for (ProductDTO wcProduct : wcProducts) {
            saveCategoryAndProduct(wcProduct);
        }
    }

    @Override
    public void importProducts(List<ProductDTO> wcProducts) {
        for (ProductDTO wcProduct : wcProducts) {
            saveCategoryAndProduct(wcProduct);
        }
    }

    private List<WCProductResponse> getProductsFromWooCommerce() {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        WooCommerceCredentialsDTO foundCreds = findByCompany(loggedInCompany.getTitle());
        URI uri = URI.create(foundCreds.getBaseUrl());
        return wooCommerceClient.getProducts(uri, foundCreds.getConsumerKey(), foundCreds.getConsumerSecret());
    }

    private List<ProductDTO> getConvertedProducts(List<WCProductResponse> list) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        list.forEach(wcProductResponse -> {
            ProductDTO productDto = createProductDto(wcProductResponse);
            productDTOList.add(productDto);
        });
        return productDTOList;
    }


    private ProductDTO createProductDto(WCProductResponse wcProduct) {
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
        productDTO.setCategory(categoryDTO);
        return productDTO;
    }

    private void saveCategoryAndProduct(ProductDTO productDTO) {
        if (!categoryService.checkIfCategoryExists(productDTO.getCategory().getDescription())) {
            categoryService.save(productDTO.getCategory());
        }
        if (!productService.checkIfProductExists(productDTO.getName())) {
            productService.save(productDTO);
        }
    }
}
