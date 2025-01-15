package com.accounting.einvoices.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class WooCommerceAuthInterceptor implements RequestInterceptor {

    private final String consumerKey;
    private final String consumerSecret;

    public WooCommerceAuthInterceptor(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String credentials = consumerKey + ":" + consumerSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        requestTemplate.header("Authorization", "Basic " + encodedCredentials);
    }
}
