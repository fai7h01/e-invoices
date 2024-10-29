package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.service.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;
    private final UserService userService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;

    public DashboardServiceImpl(InvoiceService invoiceService, UserService userService, ClientVendorService clientVendorService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
    }

    @Override
    public Map<String, BigDecimal> summaryNumbers() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("total_cost", invoiceService.countTotalCost());
        map.put("total_sales", invoiceService.countTotalSales());
        map.put("total_profit_loss", invoiceService.sumProfitLoss());
        return map;
    }

    @Override
    public Map<String, Integer> summaryQuantities() {
        Map<String, Integer> map = new HashMap<>();
        map.put("total_employees", userService.findAll().size());
        map.put("total_clients", clientVendorService.findAll().size());
        map.put("total_products", productService.findAll().size());
        return map;
    }

}
