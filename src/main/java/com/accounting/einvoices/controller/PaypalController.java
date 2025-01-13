package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.request.paypal.CatalogProductRequest;
import com.accounting.einvoices.dto.request.paypal.PlanRequest;
import com.accounting.einvoices.dto.request.paypal.pricing.PlanPricingRequest;
import com.accounting.einvoices.dto.request.paypal.subscription.SubscriptionReason;
import com.accounting.einvoices.dto.request.paypal.subscription.SubscriptionRequest;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.dto.response.paypal.*;
import com.accounting.einvoices.service.PaypalService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/paypal")
public class PaypalController {

    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/create/product")
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody CatalogProductRequest request) {
        CatalogProductResponse response = paypalService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Catalog product is successfully created.")
                .data(response)
                .build());
    }

    @GetMapping("/list/products")
    public ResponseEntity<ResponseWrapper> getProducts() {
        CatalogProductListResponse response = paypalService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Catalog product list is successfully retrieved.")
                .data(response)
                .build());
    }

    @GetMapping("/details/product/{id}")
    public ResponseEntity<ResponseWrapper> getProductDetails(@PathVariable("id") String productId) {
        CatalogProductDetailsResponse response = paypalService.getProductDetails(productId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Catalog product details is successfully retrieved.")
                .data(response)
                .build());
    }

    @PostMapping("/create/plan")
    public ResponseEntity<ResponseWrapper> createPlan(@RequestBody PlanRequest request) {
        PlanResponse response = paypalService.createPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Subscription Plan is successfully created.")
                .data(response)
                .build());
    }

    @GetMapping("/list/plans")
    public ResponseEntity<ResponseWrapper> getPlans() {
        PlanListResponse response = paypalService.getPlans();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Subscription Plans is successfully retrieved.")
                .data(response)
                .build());
    }

    @GetMapping("/details/plan/{id}")
    public ResponseEntity<ResponseWrapper> getPlanDetails(@PathVariable("id") String planId) {
        PlanDetailsResponse response = paypalService.getPlanDetails(planId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Subscription Plan details is successfully retrieved.")
                .data(response)
                .build());
    }

    @PatchMapping("/update/plan/{id}")
    public ResponseEntity<ResponseWrapper> updatePlan(@PathVariable("id") String id,
                                                      @RequestBody JsonPatch request) {
        paypalService.updatePlan(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json-patch+json")
                .body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Subscription Plan is successfully updated.")
                .build());
    }

    @PostMapping("/activate/plan/{id}")
    public ResponseEntity<ResponseWrapper> activatePlan(@PathVariable("id") String id, @RequestBody String any) {
        paypalService.activatePlan(id, any);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseWrapper.builder()
                .code(HttpStatus.ACCEPTED.value())
                .success(true)
                .message("Subscription Plan is successfully activated.")
                .build());
    }

    @PostMapping("/deactivate/plan/{id}")
    public ResponseEntity<ResponseWrapper> deactivatePlan(@PathVariable("id") String id, @RequestBody String any) {
        paypalService.deactivatePlan(id, any);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseWrapper.builder()
                .code(HttpStatus.ACCEPTED.value())
                .success(true)
                .message("Subscription Plan is successfully deactivated.")
                .build());
    }

    @PostMapping("/update/plan/pricing/{id}")
    public ResponseEntity<ResponseWrapper> updatePlanPricing(@PathVariable("id") String id,
                                                             @RequestBody PlanPricingRequest request) {
        paypalService.updatePlanPricing(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Subscription Plan pricing is successfully updated.")
                .build());
    }

    @PostMapping("/create/subscription")
    public ResponseEntity<ResponseWrapper> createSubscription(@RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = paypalService.createSubscription(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Subscription is successfully created.")
                .data(response)
                .build());
    }

    @GetMapping("/details/subscription/{id}")
    public ResponseEntity<ResponseWrapper> getSubscriptionDetails(@PathVariable("id") String id) {
        SubscriptionDetailsResponse response = paypalService.getSubscriptionDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Subscription Details is successfully retrieved.")
                .data(response)
                .build());
    }

    @PostMapping("/suspend/subscription/{id}")
    public ResponseEntity<ResponseWrapper> suspendSubscription(@PathVariable("id") String id,
                                                               @RequestBody SubscriptionReason reason) {
        paypalService.suspendSubscription(id, reason);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseWrapper.builder()
                .code(HttpStatus.ACCEPTED.value())
                .success(true)
                .message("Subscription status changed to SUSPENDED.")
                .build());
    }

}
