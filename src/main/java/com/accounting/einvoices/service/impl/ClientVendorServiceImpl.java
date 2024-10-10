package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.entity.ClientVendor;
import com.accounting.einvoices.repository.ClientVendorRepository;
import com.accounting.einvoices.service.ClientVendorService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final CompanyService companyService;
    private final MapperUtil mapperUtil;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, CompanyService companyService, MapperUtil mapperUtil) {
        this.clientVendorRepository = clientVendorRepository;
        this.companyService = companyService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public ClientVendorDTO create(ClientVendorDTO clientVendor) {
        CompanyDTO loggedInUserCompany = companyService.getByLoggedInUser();
        clientVendor.setCompany(loggedInUserCompany);
        ClientVendor saved = clientVendorRepository.save(mapperUtil.convert(clientVendor, new ClientVendor()));
        return mapperUtil.convert(saved, new ClientVendorDTO());
    }

    @Override
    public List<ClientVendorDTO> findAll() {
        CompanyDTO loggedInUserCompany = companyService.getByLoggedInUser();
        List<ClientVendor> clientVendorList = clientVendorRepository.findAllByCompanyTitle(loggedInUserCompany.getTitle());
        return clientVendorList.stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .toList();
    }

}
