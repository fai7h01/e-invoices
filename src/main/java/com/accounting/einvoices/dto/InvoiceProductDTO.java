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

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal tax;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal total;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal profitLoss;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer remainingQuantity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private InvoiceDTO invoice;

    private ProductDTO product;

    private boolean ingested;

}
