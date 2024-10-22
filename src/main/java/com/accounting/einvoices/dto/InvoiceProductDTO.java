package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    private Integer tax;

    private BigDecimal total;

    private Integer remainingQuantity;

    private InvoiceDTO invoice;

    private ProductDTO product;

}
