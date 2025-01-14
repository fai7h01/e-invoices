package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.PaypalProductClient;
import com.accounting.einvoices.client.PaypalPlanClient;
import com.accounting.einvoices.client.PaypalSubscriptionClient;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.dto.request.paypal.plan.CatalogProductRequest;
import com.accounting.einvoices.dto.request.paypal.plan.PlanRequest;
import com.accounting.einvoices.dto.request.paypal.pricing.PlanPricingRequest;
import com.accounting.einvoices.dto.request.paypal.subscription.SubscriptionReason;
import com.accounting.einvoices.dto.request.paypal.subscription.SubscriptionRequest;
import com.accounting.einvoices.dto.response.paypal.*;
import com.accounting.einvoices.entity.Company;
import com.accounting.einvoices.entity.Subscription;
import com.accounting.einvoices.repository.SubscriptionRepository;
import com.accounting.einvoices.service.PaypalService;
import com.accounting.einvoices.service.UserService;
import com.accounting.einvoices.util.MapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {

    private final PaypalProductClient paypalProductClient;
    private final PaypalPlanClient paypalPlanClient;
    private final PaypalSubscriptionClient paypalSubscriptionClient;
    private final UserService userService;
    private final MapperUtil mapperUtil;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public CatalogProductResponse createProduct(CatalogProductRequest request) {
        return paypalProductClient.createProduct(request);
    }

    @Override
    public CatalogProductListResponse getProducts() {
        return paypalProductClient.getProductList();
    }

    @Override
    public CatalogProductDetailsResponse getProductDetails(String id) {
        return paypalProductClient.getProductDetails(id);
    }

    @Override
    public PlanResponse createPlan(PlanRequest request) {
        return paypalPlanClient.createPlan(request);
    }

    @Override
    public PlanListResponse getPlans() {
        return paypalPlanClient.getPlans();
    }

    @Override
    public PlanDetailsResponse getPlanDetails(String id) {
        return paypalPlanClient.getPlanDetails(id);
    }

    @Override
    public void updatePlan(String id, JsonPatch request) {
        paypalPlanClient.updatePlan(id, request);
    }

    @Override
    public void activatePlan(String id, String any) {
        paypalPlanClient.activatePlan(id, any);
    }

    @Override
    public void deactivatePlan(String id, String any) {
        paypalPlanClient.deactivatePlan(id, any);
    }

    @Override
    public void updatePlanPricing(String id, PlanPricingRequest request) {
        paypalPlanClient.updatePlanPricing(id, request);
    }

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        return paypalSubscriptionClient.createSubscription(request);
    }

    @Override
    public SubscriptionDetailsResponse getSubscriptionDetails(String id) {
        return paypalSubscriptionClient.getSubscriptionDetails(id);
    }

    @Override
    public void suspendSubscription(String id, SubscriptionReason reason) {
        paypalSubscriptionClient.suspendSubscription(id, reason);
    }

    @Override
    public void cancelSubscription(String id, SubscriptionReason reason) {
        paypalSubscriptionClient.cancelSubscription(id, reason);
    }

    @Override
    public void activateSubscription(String id, SubscriptionReason reason) {
        paypalSubscriptionClient.activateSubscription(id, reason);
    }

    @Override
    public void handleWebHookEvent(String payload, Map<String, String> headers) {
        if (verifyWebhookSignature(payload, headers)) {
            try {
                // Parse the payload
                ObjectMapper mapper = new ObjectMapper();
                JsonNode event = mapper.readTree(payload);
                String eventType = event.get("event_type").asText();

                switch (eventType) {
                    case "BILLING.SUBSCRIPTION.ACTIVATED":
                        handleSubscriptionActivated(event);
                        break;
                    // Add other cases for events you wish to handle
                    default:
                        System.out.println("Unhandled event type: " + eventType);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to parse webhook payload", e);
            }
        } else {
            throw new RuntimeException("Webhook verification failed");
        }
    }

    private void handleSubscriptionActivated(JsonNode event) {

        String subscriptionId = event.get("resource").get("id").asText();
        String planId = event.get("resource").get("plan_id").asText();
        String status = event.get("resource").get("status").asText();
        JsonNode subscriberNode = event.get("resource").get("subscriber");

        String subscriberEmail = subscriberNode.get("email_address").asText();
        UserDTO userDto = userService.findByUsername(subscriberEmail);
        CompanyDTO company = userDto.getCompany();

        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(subscriptionId);
        subscription.setPlanId(planId);
        subscription.setStatus(status);
        subscription.setCompany(mapperUtil.convert(company, new Company()));
        subscriptionRepository.save(subscription);
    }

    private boolean verifyWebhookSignature(String payload, Map<String, String> headers) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            String transmissionId = headers.get("PAYPAL-TRANSMISSION-ID");
            String transmissionTime = headers.get("PAYPAL-TRANSMISSION-TIME");
            String signature = headers.get("PAYPAL-TRANSMISSION-SIG");
            String certUrl = headers.get("PAYPAL-CERT-URL");

            if (transmissionId == null || transmissionTime == null || signature == null || certUrl == null) {
                return false; // Missing necessary headers for verification
            }

            // Fetch the certificate from the URL
            URL certUrlObj = new URL(certUrl);
            try (InputStream in = certUrlObj.openStream()) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                X509Certificate cert = (X509Certificate) cf.generateCertificate(in);

                // Construct the data to hash
                String dataToHash = transmissionId + transmissionTime + payload;
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

                // Convert the provided signature to bytes
                byte[] signatureBytes = hexToBytes(signature);

                // Verify the signature
                Signature sig = Signature.getInstance("SHA256withRSA");
                sig.initVerify(cert.getPublicKey());
                sig.update(dataToHash.getBytes(StandardCharsets.UTF_8));
                return sig.verify(signatureBytes);
            }
        } catch (Exception e) {
            log.error("Verification Failed! Message: {}", e.getMessage());
            return false; // Verification failed
        }
    }


    private byte[] hexToBytes(String hexString) {

        if (hexString == null || hexString.isEmpty()) {
            throw new IllegalArgumentException("Hex string is null or empty.");
        }
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have an even length.");
        }

        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }
}
