package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> findAll();

    InvoiceDTO findById(Long id);

    InvoiceDTO save(InvoiceDTO invoice);

    InvoiceDTO update(Long id, InvoiceDTO invoice);

    void delete(Long id);

    void setPriceTaxAndTotal(InvoiceDTO invoice);

    void approve(Long id);

}
