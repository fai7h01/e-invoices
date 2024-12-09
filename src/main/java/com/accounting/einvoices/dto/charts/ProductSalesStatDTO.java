package com.accounting.einvoices.dto.charts;

import com.accounting.einvoices.enums.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSalesStatDTO {

    private String month;
    private String dayOfMonth;
    private int quantity;
    private BigDecimal amount;
    private Currency currency;

}
