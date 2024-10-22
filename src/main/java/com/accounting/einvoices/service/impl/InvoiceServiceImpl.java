package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.exception.InvoiceNotFoundException;
import com.accounting.einvoices.repository.InvoiceRepository;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final InvoiceProductService invoiceProductService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil, InvoiceProductService invoiceProductService) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceProductService = invoiceProductService;
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
    public InvoiceDTO save(InvoiceDTO invoice) {
        return null;
    }

    @Override
    public InvoiceDTO update(Long id, InvoiceDTO invoice) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void setPriceTaxAndTotal(InvoiceDTO invoice) {
        List<InvoiceProductDTO> invoiceProductDtoList = invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoice.getId());
        BigDecimal totalPrice = invoiceProductDtoList.stream().map(invoiceProductService::getTotalWithoutTax).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalWithTax = invoiceProductDtoList.stream().map(invoiceProductService::getTotalWithTax).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalTax = totalWithTax.subtract(totalPrice);
        invoice.setPrice(totalPrice);
        invoice.setTax(totalTax);
        invoice.setTotal(totalWithTax);
    }

    @Override
    public void approve(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoiceProductService.updateQuantityInStock(id);

        invoiceRepository.save(invoice);

    }
}
