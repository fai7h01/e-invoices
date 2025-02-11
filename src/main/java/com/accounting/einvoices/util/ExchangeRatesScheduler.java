package com.accounting.einvoices.util;

import com.accounting.einvoices.service.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExchangeRatesScheduler {

    private final CurrencyExchangeService currencyExchangeService;
    private final CacheManager cacheManager;

    public ExchangeRatesScheduler(CurrencyExchangeService currencyExchangeService, CacheManager cacheManager) {
        this.currencyExchangeService = currencyExchangeService;
        this.cacheManager = cacheManager;
    }

//    @Scheduled(fixedRate = 86_400_000)
//    public void refreshExchangeRatesCache() {
//
//        currencyExchangeService.exchangeRatesOf("GEL", 1L);
//        currencyExchangeService.exchangeRatesOf("USD", 1L);
//        currencyExchangeService.exchangeRatesOf("EUR", 1L);
//
////        Objects.requireNonNull(cacheManager.getCache("exchangeRates")).put("rates", gelRates);
////        Objects.requireNonNull(cacheManager.getCache("exchangeRates")).put("rates", usdRates);
////        Objects.requireNonNull(cacheManager.getCache("exchangeRates")).put("rates", eurRates);
//
//        log.info("Exchange rates cache refreshed!");
//    }
}
