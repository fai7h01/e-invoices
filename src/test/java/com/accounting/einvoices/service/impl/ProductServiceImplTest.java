package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.entity.Product;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.repository.ProductRepository;
import com.accounting.einvoices.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    MapperUtil mapperUtil;

    @InjectMocks
    ProductServiceImpl productService;


    @Test
    void testFindProductsByCreatedAtBetweenMonth() {

        int year = 2024;
        int startMonth = 3;
        int endMonth = 9;
        Currency currency = Currency.USD;

        Product product1 = new Product();
        product1.setName("Product1");
        product1.setCreatedAt(LocalDate.of(2024, 4, 1));
        product1.setCurrency(currency);

        Product product2 = new Product();
        product2.setName("Product2");
        product2.setCreatedAt(LocalDate.of(2024, 8, 1));
        product2.setCurrency(currency);

        List<Product> productEntityList = List.of(product1, product2);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setName("Product1");
        productDTO1.setCreatedAt(LocalDate.of(2024, 4, 1));
        productDTO1.setCurrency(currency);


        ProductDTO productDTO2 = new ProductDTO();
        productDTO1.setName("Product2");
        productDTO1.setCreatedAt(LocalDate.of(2024, 8, 1));
        productDTO1.setCurrency(currency);

        List<ProductDTO> productDTOList = List.of(productDTO1, productDTO2);

        when(productRepository.findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, currency)).thenReturn(productEntityList);

        for (int i = 0; i < productEntityList.size(); i++) {
            Product product = productEntityList.get(i);
            ProductDTO productDTO = productDTOList.get(i);
            when(mapperUtil.convert(eq(product), any(ProductDTO.class))).thenReturn(productDTO);
        }

        List<ProductDTO> result = productService.findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, currency.name());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(productDTOList.get(0), result.get(0));
        assertEquals(productDTOList.get(1), result.get(1));

        verify(productRepository, times(1)).findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, currency);
        verify(mapperUtil, times(productEntityList.size())).convert(any(Product.class), any(ProductDTO.class));

    }

}