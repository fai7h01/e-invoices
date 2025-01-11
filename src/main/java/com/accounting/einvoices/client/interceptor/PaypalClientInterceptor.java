package com.accounting.einvoices.client.interceptor;

import com.accounting.einvoices.client.PaypalAuthClient;
import com.accounting.einvoices.config.PaypalConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class PaypalClientInterceptor implements RequestInterceptor {

    private final PaypalConfig paypalConfig;
    private final PaypalAuthClient paypalAuthClient;

    private final AtomicReference<String> accessToken = new AtomicReference<>();

    @Override
    public void apply(RequestTemplate requestTemplate) {

        if (accessToken.get() == null) {
            fetchAccessToken();
        }
        requestTemplate.header("Authorization", "Bearer " + accessToken.get());

    }

    private void fetchAccessToken() {
        String basicAuth = "Basic " + Base64Utils.encodeToString((paypalConfig.apiContext().getClientID() + ":" + paypalConfig.apiContext().getClientSecret()).getBytes());

        Map<String, Object> response = paypalAuthClient.getAccessToken(basicAuth, "client_credentials");

        accessToken.set((String) response.get("access_token"));
    }
}
