package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ai_analysis.ClientAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.ProductAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.SalesAnalysisDTO;

public interface AIReportingService {

    SalesAnalysisDTO getSalesAnalysis(int year, int startMonth, int endMonth);

    InvoiceAnalysisDTO getInvoiceAnalysis(int year, int startMonth, int endMonth);

    //get product analysis
    //ProductAnalysisDTO getProductAnalysis();

    //get client analysis
    ClientAnalysisDTO getClientAnalysis();
}
