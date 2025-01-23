package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.SalesAnalysisDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.service.AIReportingService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AIReportingServiceImpl implements AIReportingService {

    private final ReportingService reportingService;
    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;

    public AIReportingServiceImpl(ReportingService reportingService, InvoiceService invoiceService, InvoiceProductService invoiceProductService) {
        this.reportingService = reportingService;
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
    }

    @Override
    public SalesAnalysisDTO getSalesAnalysis(int year, int startMonth, int endMonth) {
        String baseCurrency = Currency.USD.name();

        SalesAnalysisDTO salesAnalysisDTO = new SalesAnalysisDTO();

        Map<String, BigDecimal> financialSummary = reportingService.getFinancialSummaryBasedOnCurrentSales(year, startMonth, endMonth, baseCurrency);

        salesAnalysisDTO.setTotalSales(financialSummary.get("total_sales"));
        salesAnalysisDTO.setTotalCost(financialSummary.get("total_cost"));
        salesAnalysisDTO.setProfitLoss(financialSummary.get("profit_loss"));
        salesAnalysisDTO.setTimeframe("Between: " + LocalDate.of(year, startMonth, 1) + LocalDate.of(year, endMonth, 1));

        return salesAnalysisDTO;
    }


    @Override
    public InvoiceAnalysisDTO getInvoiceAnalysis(int year, int startMonth, int endMonth) {

        InvoiceAnalysisDTO invoiceAnalysisDTO = new InvoiceAnalysisDTO();

        List<InvoiceDTO> invoices = invoiceService.findAllByDateOfIssue(year, startMonth, endMonth);

        invoiceAnalysisDTO.setTotalInvoicesNumber(invoices.size());
        invoiceAnalysisDTO.setTotalApprovedInvoicesNumber((int) invoices.stream()
                .filter(invoice -> invoice.getInvoiceStatus().equals(InvoiceStatus.APPROVED)).count());
        invoiceAnalysisDTO.setTotalOverDueInvoicesNumber((int) invoices.stream()
                .filter(invoice -> invoice.getDueDate().isBefore(LocalDateTime.now())).count());
        invoiceAnalysisDTO.setTotalPendingInvoicesNumber((int) invoices.stream()
                .filter(invoice -> !invoice.getInvoiceStatus().equals(InvoiceStatus.APPROVED)).count());

        return invoiceAnalysisDTO;
    }
}


























