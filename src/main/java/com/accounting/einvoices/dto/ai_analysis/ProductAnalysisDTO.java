package com.accounting.einvoices.dto.ai_analysis;

import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.ProductUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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
