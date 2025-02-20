package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.dto.response.currencyExchange.CurrencyExchangeDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.CurrencyExchangeService;
import com.accounting.einvoices.service.DashboardService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final ReportingService reportingService;
    private final InvoiceService invoiceService;
    private final CurrencyExchangeService currencyExchangeService;

    public DashboardController(DashboardService dashboardService, ReportingService reportingService, InvoiceService invoiceService,
                               CurrencyExchangeService currencyExchangeService) {
        this.dashboardService = dashboardService;
        this.reportingService = reportingService;
        this.invoiceService = invoiceService;
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/financial-summary/current-sales/{year}/{startMonth}/{endMonth}/{code}")
    public ResponseEntity<ResponseWrapper> getFinancialSummary(@PathVariable("year") String year,
                                                   @PathVariable("startMonth") String startMonth,
                                                   @PathVariable("endMonth") String endMonth,
                                                   @PathVariable("code") String code) {

        Map<String, BigDecimal> financialSummary =
                reportingService.getFinancialSummaryBasedOnCurrentSales(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth), code);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Financial summary are successfully retrieved.")
                .data(financialSummary).build());
    }

    @GetMapping("/summary-quantities")
    public ResponseEntity<ResponseWrapper> getSummaryQuantities() {
        Map<String, Integer> summary = reportingService.getSummaryQuantities();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Summary numbers are successfully retrieved.")
                .data(summary).build());
    }

    @GetMapping("/sold-products/{year}/{startMonth}/{endMonth}/{code}")
    public ResponseEntity<ResponseWrapper> soldProductsEachDayOfMonth(@PathVariable("year") String year,
                                                                      @PathVariable("startMonth") String startMonth,
                                                                      @PathVariable("endMonth") String endMonth,
                                                                      @PathVariable("code") String code) {
        List<ProductSalesStatDTO> stats =
                dashboardService.totalProductsSoldEachDayMonthByCurrency(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth), code);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Sold products each day of month.")
                .data(stats).build());
    }


    @GetMapping("/top-selling-products/{year}/{startMonth}/{endMonth}/{code}")
    public ResponseEntity<ResponseWrapper> topSellingProducts(@PathVariable("year") String year,
                                                              @PathVariable("startMonth") String startMonth,
                                                              @PathVariable("endMonth") String endMonth,
                                                              @PathVariable("code") String code) {
        Map<String, ProductSalesStatDTO> stats =
                dashboardService.topSellingProductsDesc(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth), code);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Top Selling Products are successfully retrieved.")
                .data(stats).build());
    }

    @GetMapping("/exchange-rates/{code}")
    public ResponseEntity<ResponseWrapper> getExchangeRates(@PathVariable("code") String code,
                                                            @RequestParam(value = "amount", required = false) Long amount) {
        CurrencyExchangeDTO rates = currencyExchangeService.exchangeRatesOf(code, amount);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Exchange rates successfully retrieved.")
                .data(rates).build());
    }

    @GetMapping("/recently-approved")
    public ResponseEntity<ResponseWrapper> soldProductsEachDayOfMonth() {
        List<InvoiceDTO> invoices = invoiceService.recentlyApprovedInvoices();
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Recently approved invoices are successfully retrieved.")
                .data(invoices).build());
    }

}
