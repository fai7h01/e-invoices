package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.enums.Currency;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DashboardService {

    Map<String, BigDecimal> summaryNumbers();

    Map<String, Integer> summaryQuantities();

    //chart
    List<ProductSalesStatDTO> totalProductsSoldEachDayMonthByCurrency(int year, int month, String currency);

    Map<Pair<String, String>, String> exchangeRatePairs(String code);

}
