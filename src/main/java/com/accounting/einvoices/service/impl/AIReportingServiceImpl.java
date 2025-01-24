package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ai_analysis.ClientAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.ProductAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.SalesAnalysisDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.service.*;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final ClientVendorService clientVendorService;

    public AIReportingServiceImpl(ReportingService reportingService, InvoiceService invoiceService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService) {
        this.reportingService = reportingService;
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
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
        invoiceAnalysisDTO.setInvoices(invoices.stream()
                .map(this::createInvoiceData)
                .toList());

        
        return invoiceAnalysisDTO;
    }



    @Override
    public ClientAnalysisDTO getClientAnalysis() {
        ClientAnalysisDTO clientAnalysisDTO = new ClientAnalysisDTO();
        clientAnalysisDTO.setTotalClientNumber(findTotalClientNumber());
        clientAnalysisDTO.setClients(findAllClientData());
        return clientAnalysisDTO;
    }

    private List<ClientAnalysisDTO.ClientData> findAllClientData() {
        return clientVendorService.findAll()
                .stream()
                .map(this::createClientData)
                .toList();
    }

    private ClientAnalysisDTO.ClientData createClientData(ClientVendorDTO clientDTO) {
        ClientAnalysisDTO.ClientData clientData = new ClientAnalysisDTO.ClientData();
        clientData.setClientName(clientDTO.getName());
        clientData.setTotalInvoices(findTotalClientInvoicesNumber(clientDTO.getId()));
        clientData.setAverageInvoiceValue(findClientAverageInvoiceValue(clientDTO.getId()));
        clientData.setPaidInvoices(findClientPaidInvoicesNumber(clientDTO.getId()));
        clientData.setOverDueInvoices(findClientOverDueInvoicesNumber(clientDTO.getId()));
        return clientData;
    }

    private int findClientOverDueInvoicesNumber(Long clientId) {
        return (int) invoiceService.findAllByClientId(clientId)
                .stream()
                .filter(invoiceDTO -> invoiceDTO.getDueDate().isBefore(LocalDateTime.now()))
                .count();
    }

    private int findClientPaidInvoicesNumber(Long clientId) {
        return (int) invoiceService.findAllByClientId(clientId)
                .stream()
                .filter(invoiceDTO -> invoiceDTO.getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .count();
    }

    private BigDecimal findClientAverageInvoiceValue(Long clientId) {
        int totalClientInvoicesNumber = findTotalClientInvoicesNumber(clientId);
        BigDecimal totalInvoicesValue = invoiceService.findAllByClientId(clientId)
                .stream()
                .map(InvoiceDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalInvoicesValue.divide(BigDecimal.valueOf(totalClientInvoicesNumber), RoundingMode.HALF_UP);
    }

    private int findTotalClientInvoicesNumber(Long clientId) {
        return invoiceService.findAllByClientId(clientId).size();
    }


    private int findTotalClientNumber() {
        return clientVendorService.findAll().size();
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


























