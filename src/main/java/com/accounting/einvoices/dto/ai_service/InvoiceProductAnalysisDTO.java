package com.accounting.einvoices.dto.ai_service;

import com.accounting.einvoices.enums.Currency;

import java.math.BigDecimal;

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
