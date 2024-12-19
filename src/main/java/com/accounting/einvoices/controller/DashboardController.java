package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.dto.response.CurrencyExchangeDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.DashboardService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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


    //@RolesAllowed("Admin")
    @GetMapping("/financialSummary/{year}/{startMonth}/{endMonth}/{code}")
    public ResponseEntity<ResponseWrapper> getSummaryNumbers(@PathVariable("year") String year,
                                                             @PathVariable("startMonth") String startMonth,
                                                             @PathVariable("endMonth") String endMonth,
                                                             @PathVariable("code") String code) {

        Map<String, BigDecimal> financialSummary =
                reportingService.getFinancialSummary(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth), code);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Financial summary are successfully retrieved.")
                .data(financialSummary).build());
    }

    //@RolesAllowed("Admin")
    @GetMapping("/summaryQuantities")
    public ResponseEntity<ResponseWrapper> getSummaryQuantities() {
        Map<String, Integer> summary = reportingService.getSummaryQuantities();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Summary numbers are successfully retrieved.")
                .data(summary).build());
    }

    //@RolesAllowed("Admin")
    @GetMapping("/soldProductsBy/{year}/{startMonth}/{endMonth}/{currCode}")
    public ResponseEntity<ResponseWrapper> soldProductsEachDayOfMonth(@PathVariable("year") String year,
                                                                      @PathVariable("startMonth") String startMonth,
                                                                      @PathVariable("endMonth") String endMonth,
                                                                      @PathVariable("currCode") String code) {
        List<ProductSalesStatDTO> stats =
                dashboardService.totalProductsSoldEachDayMonthByCurrency(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth), code);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Sold products each day of month.")
                .data(stats).build());
    }


    //@RolesAllowed("Admin")
    @GetMapping("/topSellingProducts/{year}/{startMonth}/{endMonth}/{currCode}")
    public ResponseEntity<ResponseWrapper> topSellingProducts(@PathVariable("year") String year,
                                                              @PathVariable("startMonth") String startMonth,
                                                              @PathVariable("endMonth") String endMonth,
                                                              @PathVariable("currCode") String code) {
        Map<String, ProductSalesStatDTO> stats =
                dashboardService.topSellingProductsDesc(Integer.parseInt(year), Integer.parseInt(startMonth), Integer.parseInt(endMonth), code);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Top Selling Products are successfully retrieved.")
                .data(stats).build());
    }

    //@RolesAllowed("Admin")
    @GetMapping("/exchangeRates/{code}")
    public ResponseEntity<ResponseWrapper> getExchangeRates(@PathVariable("code") String code,
                                                            @RequestParam(value = "amount", required = false) Long amount) {
        CurrencyExchangeDTO rates = dashboardService.exchangeRatesOf(code, amount);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Exchange rates successfully retrieved.")
                .data(rates).build());
    }

    //@RolesAllowed("Admin")
    @GetMapping("/recentlyApproved")
    public ResponseEntity<ResponseWrapper> soldProductsEachDayOfMonth() {
        List<InvoiceDTO> invoices = invoiceService.recentlyApprovedInvoices();
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Recently approved invoices are successfully retrieved.")
                .data(invoices).build());
    }

}
