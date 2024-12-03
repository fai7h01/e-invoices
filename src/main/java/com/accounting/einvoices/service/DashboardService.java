package com.accounting.einvoices.service;

import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.Map;

public interface DashboardService {

    //get summary numbers
    Map<String, BigDecimal> summaryNumbers();

    Map<String, Integer> summaryQuantities();

    //chart
    Map<String, Integer> totalProductsSoldEachDayMonth(String year, String month);

    Map<Pair<String, String>, String> exchangeRatePairs();

//    //total products sold
//    //find approved invoices, find invoice products, find sum quantity of products
//    int getTotalQuantityOfSoldProducts();
//
//    //total sales
//    //find approved invoices, find invoice products, find sum total
//    BigDecimal getTotalSalesOfSoldProducts();
//
//    //total profitLoss
//    //find approved invoices, find invoice products, find total profitLoss
//    BigDecimal getTotalProfitLoss();
//
//    //top product
//    //find approved invoices, find top quantity of sold products
//    ProductDTO getTopSellingProduct();

//
//    //last 3 approved invoices
//    List<InvoiceDTO> getLast3ApprovedInvoices();
//
//    //last 3 transactions
}
