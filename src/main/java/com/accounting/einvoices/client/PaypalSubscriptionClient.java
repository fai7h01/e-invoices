package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.request.paypal.subscription.SubscriptionRequest;
import com.accounting.einvoices.dto.response.paypal.SubscriptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paypalSubscriptionClient", url = "https://api-m.sandbox.paypal.com")
public interface PaypalSubscriptionClient {

    @PostMapping("/v1/billing/subscriptions")
    SubscriptionResponse createSubscription(@RequestBody SubscriptionRequest request);

}
