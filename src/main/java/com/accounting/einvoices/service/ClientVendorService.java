package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ClientVendorDTO;

import java.util.List;

public interface ClientVendorService {

    List<ClientVendorDTO> findAll();

    ClientVendorDTO findById(Long id);

    ClientVendorDTO save(ClientVendorDTO clientVendor);

    ClientVendorDTO update(Long id, ClientVendorDTO clientVendor);

    void delete(Long id);

}
