package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.service.*;
import com.accounting.einvoices.util.BigDecimalUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final ProductService productService;
    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final UserService userService;
    private final ClientVendorService clientVendorService;

    public ReportingServiceImpl(ProductService productService, InvoiceService invoiceService, InvoiceProductService invoiceProductService, UserService userService, ClientVendorService clientVendorService) {
        this.productService = productService;
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
    }

    @Override
    public BigDecimal countTotalCostByDate(int year, int month, String currency) {

        //find products by createdAt

        BigDecimal totalCost = BigDecimal.ZERO;
        for (ProductDTO each : productService.findAllByCreatedDate(year, month, currency)) {
            BigDecimal price = each.getPrice();
            int quantity = each.getQuantityInStock();
            BigDecimal cost = price.multiply(BigDecimal.valueOf(quantity));
            totalCost = totalCost.add(cost);
        }
        return BigDecimalUtil.format(totalCost);
    }

    @Override
    public BigDecimal countTotalSalesByDate(int year, int month, String currency) {
        return invoiceService.findAllByLoggedInUser().stream().filter(invoiceDTO -> invoiceDTO.getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .map(InvoiceDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal sumProfitLossByDate(int year, int month, String currency) {
        return invoiceService.findAllByLoggedInUser().stream()
                .filter(invoiceDTO -> invoiceDTO.getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .map(invoiceDTO -> invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO.getId())
                        .stream().map(InvoiceProductDTO::getProfitLoss).reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Map<String, BigDecimal> getFinancialSummary(int year, int month, String currency) {

        Map<String, BigDecimal> map = new HashMap<>();

        map.put("total_cost", countTotalCostByDate(year, month, currency));
//        map.put("total_sales", countTotalSalesByDateAndCurrency());
//        map.put("total_profit_loss", sumProfitLoss());

        return map;
    }

    @Override
    public Map<String, Integer> getSummaryQuantities() {
        return Map.of("total_employees", userService.findAll().size(),
                    "total_clients", clientVendorService.findAll().size(),
                    "total_products", productService.findAll().size(),
                    "total_products_sold", invoiceProductService.sumQuantityOfSoldProducts());
    }
}
