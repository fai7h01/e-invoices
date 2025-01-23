package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.SalesAnalysisDTO;

public interface AIReportingService {

    //get sales analysis
    SalesAnalysisDTO getSalesAnalysis(int year, int startMonth, int endMonth);

    //get invoice analysis
    InvoiceAnalysisDTO getInvoiceAnalysis(int year, int startMonth, int endMonth);

    //get product analysis

    //get client analysis
}
