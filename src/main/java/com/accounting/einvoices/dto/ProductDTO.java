package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.ProductStatus;
import com.accounting.einvoices.enums.ProductUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String description;

    private Integer quantityInStock;

    private Integer lowLimitAlert;

    private BigDecimal price;

    private Currency currency;

    private LocalDate createdAt;

    private ProductUnit productUnit;

    private ProductStatus status;

    private CategoryDTO category;
}
