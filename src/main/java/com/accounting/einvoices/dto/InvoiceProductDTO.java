package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal tax;

    private BigDecimal total;

    private BigDecimal profitLoss;

    private Integer remainingQuantity;

    private InvoiceDTO invoice;

    private ProductDTO product;

}
