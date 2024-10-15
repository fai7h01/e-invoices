package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.entity.ClientVendor;
import com.accounting.einvoices.exception.ClientVendorNotFoundException;
import com.accounting.einvoices.repository.ClientVendorRepository;
import com.accounting.einvoices.service.ClientVendorService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.util.MapperUtil;
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
        List<ClientVendor> clientVendorList = clientVendorRepository.findAllByCompanyId(loggedInUserCompany.getId());
        return clientVendorList.stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .toList();
    }

    @Override
    public ClientVendorDTO update(Long id, ClientVendorDTO clientVendor) {
        return null;
    }

    @Override
    public void delete(Long id) {
        ClientVendor clientVendor = clientVendorRepository.findById(id).orElseThrow(() -> new ClientVendorNotFoundException("Client/Vendor not found."));
        clientVendor.setIsDeleted(true);
        clientVendor.setClientVendorName(clientVendor.getId() + "-" + clientVendor.getClientVendorName());
        clientVendorRepository.save(clientVendor);
    }

}
