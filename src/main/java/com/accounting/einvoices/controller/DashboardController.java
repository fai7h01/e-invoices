package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.DashboardService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final ReportingService reportingService;
    private final InvoiceService invoiceService;

    public DashboardController(DashboardService dashboardService, ReportingService reportingService, InvoiceService invoiceService) {
        this.dashboardService = dashboardService;
        this.reportingService = reportingService;
        this.invoiceService = invoiceService;
    }


    @GetMapping("/financialSummary/{year}/{month}/{code}")
    public ResponseEntity<ResponseWrapper> getSummaryNumbers(@PathVariable("year") String year,
                                                             @PathVariable("month") String month,
                                                             @PathVariable("code") String code) {

        Map<String, BigDecimal> financialSummary =
                reportingService.getFinancialSummary(Integer.parseInt(year), Integer.parseInt(month), code);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Financial summary are successfully retrieved.")
                .data(financialSummary).build());
    }

    @GetMapping("/summaryQuantities")
    public ResponseEntity<ResponseWrapper> getSummaryQuantities() {
        Map<String, Integer> summary = reportingService.getSummaryQuantities();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Summary numbers are successfully retrieved.")
                .data(summary).build());
    }

    @GetMapping("/soldProductsBy/{year}/{month}/{currCode}")
    public ResponseEntity<ResponseWrapper> soldProductsEachDayOfMonth(@PathVariable("year") String year,
                                                                      @PathVariable("month") String month,
                                                                      @PathVariable("currCode") String code) {
        List<ProductSalesStatDTO> stats =
                dashboardService.totalProductsSoldEachDayMonthByCurrency(Integer.parseInt(year), Integer.parseInt(month), code);
        if (!stats.isEmpty()) {
            return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                    .success(true)
                    .message("Sold products each day of month.")
                    .data(stats).build());
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/topSellingProducts/{year}/{month}/{currCode}")
    public ResponseEntity<ResponseWrapper> topSellingProducts(@PathVariable("year") String year,
                                                              @PathVariable("month") String month,
                                                              @PathVariable("currCode") String code) {
        List<ProductSalesStatDTO> stats = dashboardService.topSellingProductsDesc(Integer.parseInt(year), Integer.parseInt(month), code);
        if (!stats.isEmpty()) {
            return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                    .success(true)
                    .message("Top Selling Products in " + year + " " + Month.of(Integer.parseInt(month)))
                    .data(stats).build());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exchangeRates")
    public ResponseEntity<ResponseWrapper> getExchangeRates(@RequestParam("code") String code) {
        Map<Pair<String, String>, String> rates = dashboardService.exchangeRatePairs(code);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Exchange rates successfully retrieved.")
                .data(rates).build());
    }

    @GetMapping("/lastThreeApproved")
    public ResponseEntity<ResponseWrapper> soldProductsEachDayOfMonth() {
        List<InvoiceDTO> invoices = invoiceService.lastThreeApprovedInvoices();
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Last three approved invoices are successfully retrieved.")
                .data(invoices).build());
    }

    @GetMapping("/test/{date}")
    public LocalDate getCreatedAtDate(@PathVariable("date") String date) {
        return LocalDate.parse(date);
    }
}
