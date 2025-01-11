package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.response.paypal.PlanListResponse;
import com.accounting.einvoices.dto.response.paypal.PlanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paypalClient", url = "https://api-m.sandbox.paypal.com")
public interface PaypalSubscriptionClient {

    @PostMapping("/v1/billing/plans")
    PlanResponse createPlan(@RequestBody PlanRequest plan);

    @GetMapping("/v1/billing/plans")
    PlanListResponse getPlans();
}
