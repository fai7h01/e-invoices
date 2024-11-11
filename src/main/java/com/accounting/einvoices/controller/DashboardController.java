package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summaryNumbers")
    public ResponseEntity<ResponseWrapper> getSummaryNumbers() {
        Map<String, BigDecimal> summary = dashboardService.summaryNumbers();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Summary numbers are successfully retrieved.")
                .data(summary).build());
    }

    @GetMapping("/summaryQuantities")
    public ResponseEntity<ResponseWrapper> getSummaryQuantities() {
        Map<String, Integer> summary = dashboardService.summaryQuantities();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Summary numbers are successfully retrieved.")
                .data(summary).build());
    }

    @GetMapping("/soldProductsBy/{year}/{month}")
    private ResponseEntity<ResponseWrapper> soldProductsEachDayOfMonth(@PathVariable("year") String year, @PathVariable("month") String month) {
        Map<String, Integer> productsMap = dashboardService.totalProductsSoldEachDayMonth(year, month);
        return ResponseEntity.ok(ResponseWrapper.builder().code(HttpStatus.OK.value())
                .success(true)
                .message("Sold products each day of month.")
                .data(productsMap).build());
    }
}
