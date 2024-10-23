package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.exception.InvoiceNotFoundException;
import com.accounting.einvoices.repository.InvoiceRepository;
import com.accounting.einvoices.service.ClientVendorService;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final InvoiceProductService invoiceProductService;
    private final CompanyService companyService;
    private final ClientVendorService clientVendorService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil, @Lazy InvoiceProductService invoiceProductService, CompanyService companyService, ClientVendorService clientVendorService) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceProductService = invoiceProductService;
        this.companyService = companyService;
        this.clientVendorService = clientVendorService;
    }

    @Override
    public List<InvoiceDTO> findAll() {
        return invoiceRepository.findAllByCompanyId(companyService.getByLoggedInUser().getId()).stream()
                .map(invoice -> {
                    InvoiceDTO invoiceDTO = mapperUtil.convert(invoice, new InvoiceDTO());
                    setPriceTaxAndTotal(invoiceDTO);
                    return invoiceDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO findById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        return mapperUtil.convert(invoice, new InvoiceDTO());
    }

    @Override
    public InvoiceDTO save(InvoiceDTO invoice) {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        ClientVendorDTO client = clientVendorService.findByName(invoice.getClientVendor().getName());
        invoice.setCompany(loggedInCompany);
        invoice.setClientVendor(client);
        Invoice converted = mapperUtil.convert(invoice, new Invoice());
        Invoice saved = invoiceRepository.save(converted);;
        return mapperUtil.convert(saved, new InvoiceDTO());
    }

    @Override
    public InvoiceDTO update(Long id, InvoiceDTO invoice) {
        Optional<Invoice> foundInvoice = invoiceRepository.findById(id);
        if (foundInvoice.isPresent()) {
            invoice.setId(id);
            invoice.setCompany(companyService.getByLoggedInUser());
            Invoice saved = invoiceRepository.save(mapperUtil.convert(invoice, new Invoice()));
            return mapperUtil.convert(saved, new InvoiceDTO());
        }
        throw new InvoiceNotFoundException("Invoice not found.");
    }

    @Override
    public void delete(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        invoice.setIsDeleted(true);
        invoiceRepository.save(invoice);
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
