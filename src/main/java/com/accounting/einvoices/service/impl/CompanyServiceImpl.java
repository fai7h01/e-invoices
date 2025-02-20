package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.Company;
import com.accounting.einvoices.exception.company.CompanyAlreadyExistsException;
import com.accounting.einvoices.exception.company.CompanyNotFoundException;
import com.accounting.einvoices.repository.CompanyRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.KeycloakService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public CompanyDTO save(CompanyDTO company) {
        Company converted = mapperUtil.convert(company, new Company());
        Optional<Company> found = companyRepository.findByTitle(company.getTitle().toLowerCase());
        if (found.isPresent()) throw new CompanyAlreadyExistsException("Company with that name already exists.");
        converted.setTitle(company.getTitle().toLowerCase());
        Company saved = companyRepository.save(converted);
        return mapperUtil.convert(saved, new CompanyDTO());
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO company) {
        Company found = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company not found."));
        company.setId(id);
        company.getAddress().setId(found.getAddress().getId());
        Company converted = mapperUtil.convert(company, new Company());
        Company saved = companyRepository.save(converted);
        return mapperUtil.convert(saved, new CompanyDTO());
    }


    @Override
    public CompanyDTO findByTitle(String title) {
        return null;
    }

    @Override
    public CompanyDTO findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Company not found."));
        return mapperUtil.convert(company, new CompanyDTO());
    }

}
