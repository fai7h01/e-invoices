package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.response.currencyExchange.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://v6.exchangerate-api.com/v6/${exchangerate-api-key}", name = "exchangeRateClient")
public interface ExchangeRateClient {

    @GetMapping("/latest/{curr}")
    ExchangeRateResponse getExchanges(@PathVariable("curr") String curr);

}
