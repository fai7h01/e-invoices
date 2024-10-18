package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.ProductStatus;
import com.accounting.einvoices.enums.ProductUnit;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

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
