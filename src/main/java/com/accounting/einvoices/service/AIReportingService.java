package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;

import java.util.List;

public interface AIReportingService {

    List<InvoiceAnalysisDTO> getInvoiceAnalysis();



}
