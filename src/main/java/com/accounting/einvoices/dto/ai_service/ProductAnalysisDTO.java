package com.accounting.einvoices.dto.ai_service;

import com.accounting.einvoices.dto.response.woocommerce.Category;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.ProductUnit;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductAnalysisDTO {

    private String name;
    private String description;
    private int quantityInStock;
    private String createdAt;
    private BigDecimal price;
    private Currency currency;
    private ProductUnit productUnit;
    private String categoryName;
}
