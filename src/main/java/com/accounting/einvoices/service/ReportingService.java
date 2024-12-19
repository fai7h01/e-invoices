package com.accounting.einvoices.service;

import javax.naming.directory.InvalidAttributesException;
import java.math.BigDecimal;
import java.util.Map;

public interface ReportingService {

    Map<String, Integer> getSummaryQuantities();

    Map<String, BigDecimal> getFinancialSummaryByEachCurrency(int year, int startMonth, int endMonth, String currency);

    Map<String, BigDecimal> getFinancialSummaryInOneCurrency(int year, int startMonth, int endMonth, String currency);

    BigDecimal sumTotalCostsByCurrencyForPeriodAndConvert(int year, int startMonth, int endMonth, String currency) throws InvalidAttributesException;

}
