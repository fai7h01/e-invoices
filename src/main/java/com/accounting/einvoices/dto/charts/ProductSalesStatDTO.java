package com.accounting.einvoices.dto.charts;

import com.accounting.einvoices.enums.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class ProductSalesStatDTO {

    private String name;
    private Integer year;
    private Integer month;
    private Integer dayOfMonth;
    private Integer quantity;
    private BigDecimal amount;
    private Currency currency;

}
