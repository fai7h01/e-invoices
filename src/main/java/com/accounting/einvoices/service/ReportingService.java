package com.accounting.einvoices.service;

import javax.naming.directory.InvalidAttributesException;
import java.math.BigDecimal;
import java.util.Map;

public interface ReportingService {

    Map<String, Integer> getSummaryQuantities();

    Map<String, BigDecimal> getFinancialSummaryInSeparateCurrency(int year, int startMonth, int endMonth, String currency);

    Map<String, BigDecimal> getFinancialSummaryInOneCurrency(int year, int startMonth, int endMonth, String currency);

//    BigDecimal sumTotalCostsByCurrencyForPeriodAndConvert(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalCostOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalSalesOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency);

    BigDecimal countTotalCostByDateInOneCurrency(int year, int startMonth, int endMonth, String currency);

    BigDecimal countTotalSalesByDateInOneCurrency(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumProfitLossByDateInOneCurrency(int year, int startMonth, int endMonth, String currency);
}
