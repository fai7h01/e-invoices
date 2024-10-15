package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.entity.ClientVendor;
import com.accounting.einvoices.exception.ClientVendorAlreadyExistsException;
import com.accounting.einvoices.exception.ClientVendorNotFoundException;
import com.accounting.einvoices.repository.ClientVendorRepository;
import com.accounting.einvoices.service.ClientVendorService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<ClientVendor> foundClientVendor = clientVendorRepository.findByName(clientVendor.getName());
        if (foundClientVendor.isPresent())
            throw new ClientVendorAlreadyExistsException("Client/Vendor already exists.");
        clientVendor.setCompany(getLoggedInCompany());
        ClientVendor saved = clientVendorRepository.save(mapperUtil.convert(clientVendor, new ClientVendor()));
        return mapperUtil.convert(saved, new ClientVendorDTO());
    }

    @Override
    public List<ClientVendorDTO> findAll() {
        List<ClientVendor> clientVendorList = clientVendorRepository.findAllByCompanyId(getLoggedInCompany().getId());
        return clientVendorList.stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .toList();
    }

    @Override
    public ClientVendorDTO findById(Long id) {
        ClientVendor foundClientVendor = clientVendorRepository.findById(id).orElseThrow(() -> new ClientVendorNotFoundException("Client/Vendor not found."));
        return mapperUtil.convert(foundClientVendor, new ClientVendorDTO());
    }

    @Override
    public ClientVendorDTO update(Long id, ClientVendorDTO clientVendor) {
        ClientVendorDTO foundClientVendor = findById(id);
        clientVendor.setId(id);
        clientVendor.setCompany(foundClientVendor.getCompany());
        ClientVendor saved = clientVendorRepository.save(mapperUtil.convert(clientVendor, new ClientVendor()));
        return mapperUtil.convert(saved, new ClientVendorDTO());
    }

    @Override
    public void delete(Long id) {
        ClientVendor clientVendor = clientVendorRepository.findById(id).orElseThrow(() -> new ClientVendorNotFoundException("Client/Vendor not found."));
        clientVendor.setIsDeleted(true);
        clientVendor.setName(clientVendor.getId() + "-" + clientVendor.getName());
        clientVendorRepository.save(clientVendor);
    }

    private CompanyDTO getLoggedInCompany(){
        return companyService.getByLoggedInUser();
    }

}
