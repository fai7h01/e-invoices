package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.response.currencyExchange.CurrencyExchangeDTO;

import java.math.BigDecimal;
import java.util.Map;

public interface CurrencyExchangeService {

    Map<String, CurrencyExchangeDTO> getCurrencyExchangesMap();

    BigDecimal calculateTotalAndConvertToCommonCurrency(BigDecimal totalUsd, BigDecimal totalGel, BigDecimal totalEur, String currency);

    BigDecimal convertToCommonCurrency(BigDecimal total, String currentCurrency, String toCurrency);

    CurrencyExchangeDTO exchangeRatesOf(String code, Long amount);
}
