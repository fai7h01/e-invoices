package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.CompanyDTO;

public interface CompanyService {

    CompanyDTO getByLoggedInUser();

    CompanyDTO save(CompanyDTO company);

    CompanyDTO findByTitle(String title);

    CompanyDTO findById(Long id);
}
