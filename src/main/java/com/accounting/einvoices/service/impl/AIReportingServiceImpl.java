package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.SalesAnalysisDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.service.AIReportingService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AIReportingServiceImpl implements AIReportingService {

    private static final Logger log = LoggerFactory.getLogger(AIReportingServiceImpl.class);
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
        log.info("\n\n\n\n>>>>>>>> Invoices in analysis: {}", invoices);

        invoiceAnalysisDTO.setTotalInvoicesNumber(invoices.size());
        invoiceAnalysisDTO.setTotalApprovedInvoicesNumber((int) invoices.stream()
                .filter(invoice -> invoice.getInvoiceStatus().equals(InvoiceStatus.APPROVED)).count());
        invoiceAnalysisDTO.setTotalOverDueInvoicesNumber((int) invoices.stream()
                .filter(invoice -> invoice.getDueDate().isBefore(LocalDateTime.now())).count());
        invoiceAnalysisDTO.setTotalPendingInvoicesNumber((int) invoices.stream()
                .filter(invoice -> !invoice.getInvoiceStatus().equals(InvoiceStatus.APPROVED)).count());
        invoiceAnalysisDTO.setInvoices(invoices.stream()
                .map(this::createInvoiceData)
                .toList());

        
        return invoiceAnalysisDTO;
    }


    private List<String> findInvoiceItems(InvoiceDTO invoiceDTO) {
        List<InvoiceProductDTO> invoiceProducts = invoiceProductService.findAllByInvoiceId(invoiceDTO.getId());
        return invoiceProducts.stream()
                .map(invoiceProductDTO -> invoiceProductDTO.getProduct().getName())
                .distinct()
                .toList();
    }
    
    private InvoiceAnalysisDTO.InvoiceData createInvoiceData(InvoiceDTO invoiceDTO) {

        InvoiceAnalysisDTO.InvoiceData invoiceData = new InvoiceAnalysisDTO.InvoiceData();
        
        invoiceData.setInvoiceNo(invoiceDTO.getInvoiceNo());
        invoiceData.setInvoiceStatus(invoiceDTO.getInvoiceStatus());
        invoiceData.setDateOfIssue(invoiceDTO.getDateOfIssue());
        invoiceData.setDueDate(invoiceDTO.getDueDate());
        invoiceData.setAcceptDate(invoiceDTO.getAcceptDate());
        invoiceData.setPaymentTerms(invoiceDTO.getPaymentTerms());
        invoiceData.setClientName(invoiceDTO.getClientVendor().getName());
        invoiceData.setPrice(invoiceDTO.getPrice());
        invoiceData.setCurrency(invoiceDTO.getCurrency());
        invoiceData.setTax(invoiceDTO.getTax());
        invoiceData.setTotal(invoiceDTO.getTotal());
        invoiceData.setInvoiceItems(findInvoiceItems(invoiceDTO));
        
        return invoiceData;
    }
}


























