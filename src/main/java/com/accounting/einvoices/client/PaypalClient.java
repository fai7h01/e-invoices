package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.request.paypal.plans.PlanRequest;
import com.accounting.einvoices.dto.response.paypal.plans.PlanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(value = "PAYPAL", url = "https://api-m.sandbox.paypal.com")
public interface PaypalClient {

    @PostMapping("/v1/billing/plans")
    ResponseEntity<PlanResponse> createSubscriptionPlan(@RequestBody PlanRequest planRequest);

    @GetMapping("/v1/billing/plans")
    ResponseEntity<List<PlanResponse>> listSubscriptionPlans();

}
