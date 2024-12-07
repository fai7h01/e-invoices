package com.accounting.einvoices.util;

import com.accounting.einvoices.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class ExchangeRatesScheduler {

    private final DashboardService dashboardService;
    private final CacheManager cacheManager;

    public ExchangeRatesScheduler(DashboardService dashboardService, CacheManager cacheManager) {
        this.dashboardService = dashboardService;
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate = 86_400_000)
    public void refreshExchangeRatesCache() {

        Map<Pair<String, String>, String> gelRates = dashboardService.exchangeRatePairs("GEL");
        Map<Pair<String, String>, String> usdRates = dashboardService.exchangeRatePairs("USD");
        Map<Pair<String, String>, String> eurRates = dashboardService.exchangeRatePairs("EUR");

        Objects.requireNonNull(cacheManager.getCache("exchangeRates")).put("rates", gelRates);
        Objects.requireNonNull(cacheManager.getCache("exchangeRates")).put("rates", usdRates);
        Objects.requireNonNull(cacheManager.getCache("exchangeRates")).put("rates", eurRates);

        log.info("Exchange rates cache refreshed!");
    }
}
