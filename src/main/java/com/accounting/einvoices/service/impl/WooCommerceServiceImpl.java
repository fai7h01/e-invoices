package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.WooCommerceCredentialsDTO;
import com.accounting.einvoices.entity.WooCommerceCredentials;
import com.accounting.einvoices.repository.WooCommerceRepository;
import com.accounting.einvoices.service.WooCommerceService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class WooCommerceServiceImpl implements WooCommerceService {

    private final WooCommerceRepository wooCommerceRepository;
    private final MapperUtil mapperUtil;

    public WooCommerceServiceImpl(WooCommerceRepository wooCommerceRepository, MapperUtil mapperUtil) {
        this.wooCommerceRepository = wooCommerceRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public WooCommerceCredentialsDTO saveCredentials(WooCommerceCredentialsDTO dto) {
        WooCommerceCredentials converted = mapperUtil.convert(dto, new WooCommerceCredentials();
        WooCommerceCredentials saved = wooCommerceRepository.save(converted);
        return mapperUtil.convert(saved, new WooCommerceCredentialsDTO());
    }

    @Override
    public WooCommerceCredentialsDTO findByUsername(String username) {
        WooCommerceCredentials found = wooCommerceRepository.findByUser_Username(username).orElseThrow();
        return mapperUtil.convert(found, new WooCommerceCredentialsDTO());
    }
}
