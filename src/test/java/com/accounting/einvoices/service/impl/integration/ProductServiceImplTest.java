package com.accounting.einvoices.service.impl.integration;

import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.entity.Category;
import com.accounting.einvoices.entity.Product;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.repository.CategoryRepository;
import com.accounting.einvoices.repository.ProductRepository;
import com.accounting.einvoices.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ProductServiceImplTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void testFindProductsByCreatedAtBetweenMonth() {

        //Arrange
        int year = 2024;
        int startMonth = 3;
        int endMonth = 9;
        Currency currency = Currency.USD;

        Category category = new Category();
        category.setDescription("Category");

        categoryRepository.save(category);


        // Save some test data
        Product product1 = new Product();
        product1.setName("Product1");
        product1.setCreatedAt(LocalDate.of(2024, 4, 1));
        product1.setCurrency(currency);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setName("Product2");
        product2.setCreatedAt(LocalDate.of(2024, 6, 1));
        product2.setCurrency(currency);
        product2.setCategory(category);

        Product product3 = new Product();
        product3.setName("Product3");
        product3.setCreatedAt(LocalDate.of(2024, 12, 1)); // outside the range
        product3.setCurrency(currency);
        product3.setCategory(category);

        productRepository.saveAll(List.of(product1, product2, product3));

        List<ProductDTO> result = productService.findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, currency.name());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getName());
        assertEquals("Product2", result.get(1).getName());

    }

}