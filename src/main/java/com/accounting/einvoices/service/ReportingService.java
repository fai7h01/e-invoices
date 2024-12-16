package com.accounting.einvoices.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ReportingService {

    BigDecimal countTotalCostByDate(int year, int month, String currency);

    BigDecimal countTotalSalesByDate(int year, int month, String currency);

    BigDecimal sumProfitLossByDate(int year, int month, String currency);

    Map<String, Integer> getSummaryQuantities();

    Map<String, BigDecimal> getFinancialSummary(int year, int month, String currency);


}
