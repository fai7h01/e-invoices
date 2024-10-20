package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.repository.InvoiceRepository;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceDTO> findAll() {
        return List.of();
    }

    @Override
    public InvoiceDTO findById(Long id) {
        return null;
    }

    @Override
    public InvoiceDTO create(InvoiceDTO invoice) {
        return null;
    }

    @Override
    public InvoiceDTO update(Long id, InvoiceDTO invoice) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
