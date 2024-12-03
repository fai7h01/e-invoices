package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.response.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://v6.exchangerate-api.com/v6/${exchangerate-api-key}", name = "EXCHANGERATES")
public interface ExchangeRateClient {

    @GetMapping("/pair/{pair1}/{pair2}")
    ExchangeRateResponse getExchangePair(@PathVariable("pair1") String pair1,
                                         @PathVariable("pair2") String pair2);

}
