package com.accounting.einvoices.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "wooCommerceClient", url = "https://{store-url}")
public interface WooCommerceClient {


}
