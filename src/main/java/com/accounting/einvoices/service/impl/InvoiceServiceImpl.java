package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.*;
import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.exception.invoice.InvoiceNotFoundException;
import com.accounting.einvoices.repository.InvoiceRepository;
import com.accounting.einvoices.service.*;
import com.accounting.einvoices.util.BigDecimalUtil;
import com.accounting.einvoices.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final InvoiceProductService invoiceProductService;
    private final CompanyService companyService;
    private final ClientVendorService clientVendorService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil, @Lazy InvoiceProductService invoiceProductService,
                              CompanyService companyService, ClientVendorService clientVendorService) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceProductService = invoiceProductService;
        this.companyService = companyService;
        this.clientVendorService = clientVendorService;
    }

    @Override
    public List<InvoiceDTO> findAllByLoggedInUser() {
        return invoiceRepository.findAllByCompanyIdOrderByInvoiceNo(companyService.getByLoggedInUser().getId()).stream()
                .map(invoice -> {
                    InvoiceDTO invoiceDTO = mapperUtil.convert(invoice, new InvoiceDTO());
                    setPriceTaxTotal(invoiceDTO);
                    return invoiceDTO;
                }).toList();
    }


    @Override
    public List<InvoiceDTO> findAllByCompanyTitle(String company) {
        List<Invoice> invoices = invoiceRepository.findAllByCompanyTitle(company);
        return invoices.stream()
                .map(invoice -> {
                    InvoiceDTO invoiceDTO = mapperUtil.convert(invoice, new InvoiceDTO());
                    setPriceTaxTotal(invoiceDTO);
                    return invoiceDTO;
                }).toList();
    }

    @Override
    public InvoiceDTO findById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        InvoiceDTO converted = mapperUtil.convert(invoice, new InvoiceDTO());
        setPriceTaxTotal(converted);
        return converted;
    }

    @Override
    public InvoiceDTO findByInvNoAndCompanyTitle(String invNo, String company) {
        Optional<Invoice> invoice = invoiceRepository.findByInvoiceNoAndCompanyTitle(invNo, company);
        if (invoice.isEmpty()) throw new InvoiceNotFoundException("Invoice not found.");
        return mapperUtil.convert(invoice.get(), new InvoiceDTO());
    }

    @Override
    public InvoiceDTO generateInvoice() {
        InvoiceDTO invoice = new InvoiceDTO();
        int currentInvNo = this.findAllByLoggedInUser().size();
        LocalDateTime dateOfIssue = LocalDateTime.now();
        invoice.setInvoiceNo(String.format("INV%03d", currentInvNo + 1));
        invoice.setDateOfIssue(dateOfIssue);
        return invoice;
    }

    @Override
    public InvoiceDTO save(InvoiceDTO invoice) {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        ClientVendorDTO client = clientVendorService.findByName(invoice.getClientVendor().getName());
        invoice.setCompany(loggedInCompany);
        invoice.setClientVendor(client);
        Invoice converted = mapperUtil.convert(invoice, new Invoice());
        //choose currency for invoice before saving
        Invoice saved = invoiceRepository.save(converted);
        return mapperUtil.convert(saved, new InvoiceDTO());
    }

    @Override
    public InvoiceDTO update(Long id, InvoiceDTO invoice) {
        Optional<Invoice> foundInvoice = invoiceRepository.findById(id);
        if (foundInvoice.isPresent()) {
            invoice.setId(id);
            invoice.setCompany(companyService.getByLoggedInUser());
            //find clientVendor by name
            ClientVendorDTO client = clientVendorService.findByName(invoice.getClientVendor().getName());
            invoice.getClientVendor().setId(client.getId());
            Invoice saved = invoiceRepository.save(mapperUtil.convert(invoice, new Invoice()));
            return mapperUtil.convert(saved, new InvoiceDTO());
        }
        throw new InvoiceNotFoundException("Invoice not found.");
    }

    @Override
    public void delete(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        try {
            invoiceProductService.deleteAllByInvoice(id);
        }catch (InvoiceNotFoundException e) {
            log.warn("Invoice Products not found to delete: {}", e.getMessage());
        }
        invoice.setIsDeleted(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public void setPriceTaxTotal(InvoiceDTO invoice) {
        List<InvoiceProductDTO> invoiceProductDtoList = invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoice.getId());
        BigDecimal totalPrice = invoiceProductDtoList
                .stream()
                .map(invoiceProductService::getTotalWithoutTax)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalWithTax = invoiceProductDtoList
                .stream()
                .map(invoiceProductService::getTotalWithTax)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalTax = invoiceProductDtoList
                .stream()
                .map(InvoiceProductDTO::getTax)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        invoice.setPrice(BigDecimalUtil.format(totalPrice));
        invoice.setTax(BigDecimalUtil.format(totalTax));
        invoice.setTotal(BigDecimalUtil.format(totalWithTax));
    }

    @Override
    public void approve(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoice.setAcceptDate(LocalDateTime.now());
        invoiceRepository.save(invoice);
        invoiceProductService.updateQuantityInStock(id);
        invoiceProductService.calculateProfitLoss(id);
    }

    @Override
    public InvoiceDTO approve(String invNo, String companyTitle) {
        Invoice invoice = invoiceRepository.findByInvoiceNoAndCompanyTitle(invNo, companyTitle).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoice.setAcceptDate(LocalDateTime.now());
        Invoice saved = invoiceRepository.save(invoice);
        invoiceProductService.updateQuantityInStock(invoice.getId());
        invoiceProductService.calculateProfitLoss(invoice.getId());
        return mapperUtil.convert(saved, new InvoiceDTO());
    }


    @Override
    public Map<Currency, List<InvoiceDTO>> findAllByAcceptDate(int year, int startMonth, int endMonth) {
        Long companyId = companyService.getByLoggedInUser().getId();
        Map<Currency, List<InvoiceDTO>> map = invoiceRepository.findAllByYearAndMonthBetweenAndStatusAndCompany(year, startMonth, endMonth, InvoiceStatus.APPROVED, companyId)
                .stream()
                .map(invoice -> {
                    InvoiceDTO dto = mapperUtil.convert(invoice, new InvoiceDTO());
                    setPriceTaxTotal(dto);
                    return dto;
                })
                .collect(Collectors.groupingBy(InvoiceDTO::getCurrency));
        return map;
    }

    @Override
    public List<InvoiceDTO> findAllByClientId(Long id) {
        return invoiceRepository.findAllByClientVendorId(id).stream()
                .map(invoice -> mapperUtil.convert(invoice, new InvoiceDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> recentlyApprovedInvoices() {
        return invoiceRepository.findAllByCompanyIdAndInvoiceStatusOrderByDateOfIssueDesc(companyService.getByLoggedInUser().getId(),
                InvoiceStatus.APPROVED).stream()
                .map(invoice -> {
                    InvoiceDTO invoiceDTO = mapperUtil.convert(invoice, new InvoiceDTO());
                    setPriceTaxTotal(invoiceDTO);
                    return invoiceDTO;
                }).collect(Collectors.toList());
    }
}
