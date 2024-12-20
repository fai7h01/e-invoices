package com.accounting.einvoices.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ReportingService {

    Map<String, Integer> getSummaryQuantities();

    Map<String, BigDecimal> getFinancialSummaryInSeparateCurrency(int year, int startMonth, int endMonth, String currency);

    Map<String, BigDecimal> getFinancialSummaryConvertedInOneCurrency(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalCostOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalSalesOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalProfitLossEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalCostByDateInOneCurrency(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalSalesByDateInOneCurrency(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumProfitLossByDateInOneCurrency(int year, int startMonth, int endMonth, String currency);
}
