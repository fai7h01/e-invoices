package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.ProductUnit;
import lombok.*;

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

    private ProductUnit productUnit;

    private CategoryDTO category;

    private boolean hasInvoiceProduct;
}
