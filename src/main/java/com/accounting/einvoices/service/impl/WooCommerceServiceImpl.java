package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.WooCommerceClient;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.dto.WooCommerceCredentialsDTO;
import com.accounting.einvoices.dto.response.woocommerce.WCProductResponse;
import com.accounting.einvoices.entity.WooCommerceCredentials;
import com.accounting.einvoices.repository.WooCommerceRepository;
import com.accounting.einvoices.service.KeycloakService;
import com.accounting.einvoices.service.UserService;
import com.accounting.einvoices.service.WooCommerceService;
import com.accounting.einvoices.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
public class WooCommerceServiceImpl implements WooCommerceService {

    private final WooCommerceRepository wooCommerceRepository;
    private final WooCommerceClient wooCommerceClient;
    private final UserService userService;
    private final KeycloakService keycloakService;
    private final MapperUtil mapperUtil;

    public WooCommerceServiceImpl(WooCommerceRepository wooCommerceRepository, WooCommerceClient wooCommerceClient, UserService userService,
                                  KeycloakService keycloakService, MapperUtil mapperUtil) {
        this.wooCommerceRepository = wooCommerceRepository;
        this.wooCommerceClient = wooCommerceClient;
        this.userService = userService;
        this.keycloakService = keycloakService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public WooCommerceCredentialsDTO saveCredentials(WooCommerceCredentialsDTO dto) {
        log.info("WOOCOMMERCE: {}", dto);
        if (dto.getUser() == null) {
            UserDTO loggedInUser = keycloakService.getLoggedInUser();
            dto.setUser(loggedInUser);
        }
        WooCommerceCredentials converted = mapperUtil.convert(dto, new WooCommerceCredentials());
        WooCommerceCredentials saved = wooCommerceRepository.save(converted);
        return mapperUtil.convert(saved, new WooCommerceCredentialsDTO());
    }

    @Override
    public WooCommerceCredentialsDTO findByUsername(String username) {
        WooCommerceCredentials found = wooCommerceRepository.findByUser_Username(username).orElseThrow();
        return mapperUtil.convert(found, new WooCommerceCredentialsDTO());
    }

    @Override
    public List<WCProductResponse> fetchProducts() {

        UserDTO loggedInUser = keycloakService.getLoggedInUser();
        WooCommerceCredentialsDTO foundCreds = findByUsername(loggedInUser.getUsername());
        URI uri = URI.create(foundCreds.getBaseUrl());
        List<WCProductResponse> products = wooCommerceClient.getProducts(uri, foundCreds.getConsumerKey(), foundCreds.getConsumerSecret());

        return products;
    }
}
