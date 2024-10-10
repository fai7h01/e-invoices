package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.CompanyDTO;

public interface CompanyService {

    CompanyDTO getByLoggedInUser();

}
