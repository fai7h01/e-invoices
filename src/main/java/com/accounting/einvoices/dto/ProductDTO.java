package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.ProductStatus;
import com.accounting.einvoices.enums.ProductUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Integer quantityInStock;
    @NotNull
    private Integer lowLimitAlert;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Currency currency;
    @NotNull
    private LocalDate createdAt;
    @NotNull
    private ProductUnit productUnit = ProductUnit.UNIT;
    @NotNull
    private ProductStatus status;
    @NotNull
    private CategoryDTO category;
}
