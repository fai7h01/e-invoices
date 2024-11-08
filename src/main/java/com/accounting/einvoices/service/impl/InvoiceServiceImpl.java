package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.*;
import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.exception.InvoiceNotFoundException;
import com.accounting.einvoices.repository.InvoiceRepository;
import com.accounting.einvoices.service.*;
import com.accounting.einvoices.util.BigDecimalUtil;
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
    private final ProductService productService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil, @Lazy InvoiceProductService invoiceProductService, CompanyService companyService, ClientVendorService clientVendorService, ProductService productService) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.invoiceProductService = invoiceProductService;
        this.companyService = companyService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
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
    public List<InvoiceDTO> findAllByCompanyId(Long id) {
        return invoiceRepository.findAllByCompanyId(id).stream()
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
    public InvoiceDTO generateInvoice() {
        InvoiceDTO invoice = new InvoiceDTO();
        int currentInvNo = findAll().size();
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
        BigDecimal totalTax = invoiceProductDtoList.stream().map(InvoiceProductDTO::getTax).reduce(BigDecimal.ZERO, BigDecimal::add); //get total tax
        invoice.setPrice(BigDecimalUtil.format(totalPrice));
        invoice.setTax(BigDecimalUtil.format(totalTax));
        invoice.setTotal(BigDecimalUtil.format(totalWithTax));
    }

    @Override
    public void approve(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found."));
        invoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        invoiceRepository.save(invoice);
        invoiceProductService.updateQuantityInStock(id);
        invoiceProductService.calculateProfitLoss(id);
    }

    @Override
    public BigDecimal countTotalCost() {
        List<ProductDTO> products = productService.findAll();
        BigDecimal totalCost = BigDecimal.ZERO;
        for (ProductDTO each : products) {
            BigDecimal price = each.getPrice();
            int quantity = each.getQuantityInStock();
            BigDecimal cost = price.multiply(BigDecimal.valueOf(quantity));
            totalCost = totalCost.add(cost);
        }
        return BigDecimalUtil.format(totalCost);
    }

    @Override
    public BigDecimal countTotalSales() {
        return findAll().stream().filter(invoiceDTO -> invoiceDTO.getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .map(InvoiceDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal sumProfitLoss() {
        return findAll().stream()
                .filter(invoiceDTO -> invoiceDTO.getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .map(invoiceDTO -> invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO.getId())
                        .stream().map(InvoiceProductDTO::getProfitLoss).reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
