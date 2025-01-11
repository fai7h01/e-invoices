package com.accounting.einvoices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "paypalAuthClient", url = "https://api-m.sandbox.paypal.com")
public interface PaypalAuthClient {

    @PostMapping
    Map<String, Object> getAccessToken(@RequestHeader("Authorization") String basicAuth,
                                       @RequestParam("grant_type") String grantType);
}
