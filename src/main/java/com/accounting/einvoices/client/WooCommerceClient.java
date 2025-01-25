package com.accounting.einvoices.client;

import com.accounting.einvoices.dto.response.woocommerce.WCProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;

//@FeignClient(name = "wooCommerceClient", url = "https://this-is-a-placeholder.com", configuration = FeignClientConfig.class)
@FeignClient(name = "wooCommerceClient", url = "https://this-is-a-placeholder.com")
public interface WooCommerceClient {

    @GetMapping("/wp-json/wc/v3/products")
    List<WCProductResponse> getProducts(
            URI baseUrl,
            @RequestParam("consumer_key") String consumerKey,
            @RequestParam("consumer_secret") String consumerSecret
    );

}
