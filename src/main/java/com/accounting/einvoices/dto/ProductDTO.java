package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.ProductStatus;
import com.accounting.einvoices.enums.ProductUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private Integer quantityInStock;

    private Integer lowLimitAlert;

    private LocalDate createdAt;

    private ProductUnit productUnit;

    private ProductStatus status;

    private CategoryDTO category;

//    private boolean hasInvoiceProduct;
}
