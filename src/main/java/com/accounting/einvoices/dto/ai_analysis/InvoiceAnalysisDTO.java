package com.accounting.einvoices.dto.ai_analysis;

import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;


import java.util.List;

public class InvoiceAnalysisDTO {

    private String invoiceNo;

    private InvoiceStatus invoiceStatus;

    private String dateOfIssue;

    private String dueDate;

    private String acceptDate;

    private Currency currency;

    private String paymentTerms;

    private String notes;

    private String clientName;

    private List<InvoiceProductAnalysisDTO> items;

}
