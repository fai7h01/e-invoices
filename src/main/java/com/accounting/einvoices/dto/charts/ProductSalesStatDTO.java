package com.accounting.einvoices.dto.charts;

import com.accounting.einvoices.enums.Currency;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ProductSalesStatDTO {

    private String month;
    private String dayOfMonth;
    private int quantity;
    private BigDecimal amount;
    private Currency currency;

}
