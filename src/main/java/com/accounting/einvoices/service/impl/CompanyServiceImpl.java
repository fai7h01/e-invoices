package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.repository.CompanyRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.KeycloakService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final KeycloakService keycloakService;
    private final MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, KeycloakService keycloakService, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.keycloakService = keycloakService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public CompanyDTO getByLoggedInUser() {
        UserDTO loggedInUser = keycloakService.getLoggedInUser();
        return loggedInUser.getCompany();
    }
}
