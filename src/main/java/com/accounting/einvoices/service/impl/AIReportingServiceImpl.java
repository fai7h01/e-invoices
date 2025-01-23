package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ai_analysis.SalesAnalysisDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.service.AIReportingService;
import com.accounting.einvoices.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class AIReportingServiceImpl implements AIReportingService {

    private final ReportingService reportingService;

    public AIReportingServiceImpl(ReportingService reportingService) {
        this.reportingService = reportingService;
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
}
