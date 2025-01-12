package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.request.paypal.PlanUpdateRequest;
import com.accounting.einvoices.dto.response.paypal.PlanDetailsResponse;
import com.accounting.einvoices.dto.response.paypal.PlanListResponse;
import com.accounting.einvoices.dto.response.paypal.PlanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@FeignClient(name = "paypalSubscriptionClient", url = "https://api-m.sandbox.paypal.com")
public interface PaypalSubscriptionClient {

    @PostMapping("/v1/billing/plans")
    PlanResponse createPlan(@RequestBody PlanRequest plan);

    @GetMapping("/v1/billing/plans")
    PlanListResponse getPlans();

    @GetMapping("/v1/billing/plans/{id}")
    PlanDetailsResponse getPlanDetails(@PathVariable("id") String id);

    @PatchMapping(value = "/v1/billing/plans/{id}", consumes = MediaType.APPLICATION_JSON_PATCH_JSON)
    void updatePlan(@PathVariable("id") String id, @RequestBody PlanUpdateRequest request);

    @PostMapping("/v1/billing/plans/{id}/activate")
    void activatePlan(@PathVariable("id") String id, @RequestBody String any);
}

