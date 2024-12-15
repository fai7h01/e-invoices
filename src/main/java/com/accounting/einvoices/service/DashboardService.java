package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.dto.response.CurrencyExchangeDTO;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

public interface DashboardService {


    CurrencyExchangeDTO exchangeRatePairs(String code);

    //chart
    List<ProductSalesStatDTO> totalProductsSoldEachDayMonthByCurrency(int year, int month, String currency);

    //top 3 selling products by year and month
    List<ProductSalesStatDTO> topSellingProductsDesc(int year, int month, String currency);



}
