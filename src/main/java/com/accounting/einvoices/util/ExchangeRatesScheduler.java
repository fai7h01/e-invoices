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

    @Scheduled(fixedRate = 21600000)
    public void refreshExchangeRatesCache() {

        Map<Pair<String, String>, String> rates = dashboardService.exchangeRatePairs();

        Objects.requireNonNull(cacheManager.getCache("exchangeRates")).put("rates", rates);

        log.info("Exchange rates cache refreshed!");
    }
}
