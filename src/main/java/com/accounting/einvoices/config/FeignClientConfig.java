package com.accounting.einvoices.config;

import feign.Client;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class FeignClientConfig {

    @Bean
    public Client feignClient() {
        return new Client.Default(createUnsafeHttpClient(), null);
    }

    private SSLSocketFactory createUnsafeHttpClient() {
        try {
            TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial(acceptingTrustStrategy)
                    .build();

            return sslContext.getSocketFactory();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create an Unsafe SSL Socket Factory", e);
        }
    }
}
