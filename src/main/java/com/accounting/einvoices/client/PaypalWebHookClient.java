package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.request.paypal.webhook.WebHookRequest;
import com.accounting.einvoices.dto.response.paypal.WebHookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paypalWebHookClient", url = "https://api-m.sandbox.paypal.com")
public interface PaypalWebHookClient {

    @PostMapping("/v1/notifications/webhooks")
    WebHookResponse createWebHook(@RequestBody WebHookRequest request);

}
