package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ClientVendorDTO;

import java.util.List;

public interface ClientVendorService {

    ClientVendorDTO create(ClientVendorDTO clientVendor);

    List<ClientVendorDTO> findAll();

}