package com.accounting.einvoices.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ReportingService {

    Map<String, Integer> getSummaryQuantities();

    Map<String, BigDecimal> getFinancialSummaryBasedOnCurrentSales(int year, int startMonth, int endMonth, String currency);

}
