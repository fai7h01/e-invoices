package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ai_analysis.ClientAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceProductAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.ProductAnalysisDTO;

import java.util.List;

public interface AIReportingService {

    List<InvoiceAnalysisDTO> getInvoiceAnalysis();

    List<ProductAnalysisDTO> getProductsAnalysis();

    List<InvoiceProductAnalysisDTO> getInvoiceProductAnalysis();

    List<ClientAnalysisDTO> getClientAnalysis();


}
