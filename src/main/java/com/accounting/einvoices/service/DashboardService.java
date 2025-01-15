package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;

import java.util.List;
import java.util.Map;

public interface DashboardService {

    //chart
    List<ProductSalesStatDTO> totalProductsSoldEachDayMonthByCurrency(int year, int startMonth, int endMonth, String currency);

    //top 3 selling products by year and month
    Map<String, ProductSalesStatDTO> topSellingProductsDesc(int year, int startMonth, int endMonth, String currency);




}
