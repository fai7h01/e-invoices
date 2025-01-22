package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.service.AIReportingService;
import com.accounting.einvoices.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AIReportingServiceImpl implements AIReportingService {

    private final InvoiceService invoiceService;

    public AIReportingServiceImpl(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public List<InvoiceAnalysisDTO> getInvoiceAnalysis() {
        List<InvoiceDTO> approvedInvoices = invoiceService.findAllByStatus(InvoiceStatus.APPROVED);
        return approvedInvoices.stream()
                .map(this::convertToAnalysisDTO)
                .toList();
    }

    private InvoiceAnalysisDTO convertToAnalysisDTO(InvoiceDTO invoice) {
        InvoiceAnalysisDTO invoiceAnalysisDTO = new InvoiceAnalysisDTO();
        invoiceAnalysisDTO.setInvoiceNo(invoice.getInvoiceNo());
        invoiceAnalysisDTO.setInvoiceStatus(invoice.getInvoiceStatus());
        invoiceAnalysisDTO.setDateOfIssue(invoice.getDateOfIssue().toString());
        invoiceAnalysisDTO.setDueDate(invoice.getDueDate().toString());
        invoiceAnalysisDTO.setAcceptDate(invoice.getAcceptDate().toString());
        invoiceAnalysisDTO.setCurrency(invoice.getCurrency());
        invoiceAnalysisDTO.setPaymentTerms(invoice.getPaymentTerms());
        invoiceAnalysisDTO.setNotes(invoiceAnalysisDTO.getNotes());
        invoiceAnalysisDTO.setClientName(invoice.getClientVendor().getName());
        return invoiceAnalysisDTO;
    }
}
