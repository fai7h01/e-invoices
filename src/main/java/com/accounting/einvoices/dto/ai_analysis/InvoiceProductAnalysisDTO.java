package com.accounting.einvoices.dto.ai_analysis;

import com.accounting.einvoices.enums.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceProductAnalysisDTO {

    private String invoiceNo;
    private String productName;
    private String description;
    private int quantity;
    private BigDecimal price;
    private Currency currency;
    private BigDecimal tax;
    private BigDecimal total;
    private BigDecimal profitLoss;
    private int remainingQuantity;

}
