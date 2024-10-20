package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.repository.InvoiceProductRepository;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ProductService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceService invoiceService;
    private final ProductService productService;
    private final MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, InvoiceService invoiceService, ProductService productService, MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceProductDTO> findAllByInvoiceId(Long id) {
        return invoiceProductRepository.findAllByInvoiceId(id).stream()
                .map(invoiceProduct -> {
                    InvoiceProductDTO invoiceProductDTO = mapperUtil.convert(invoiceProduct, new InvoiceProductDTO());
                    invoiceProductDTO.setTotal(getTotalWithTax(invoiceProductDTO));
                    return invoiceProductDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public InvoiceProductDTO create(Long invoiceId, InvoiceProductDTO invoiceProduct) {
        InvoiceDTO foundInvoice = invoiceService.findById(invoiceId);

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BigDecimal getTotalWithTax(InvoiceProductDTO invoiceProduct) {
        BigDecimal totalWithoutTax = getTotalWithoutTax(invoiceProduct);
        BigDecimal tax = (totalWithoutTax.multiply(BigDecimal.valueOf(invoiceProduct.getTax()))).divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
        return totalWithoutTax.add(tax);
    }

    @Override
    public BigDecimal getTotalWithoutTax(InvoiceProductDTO invoiceProduct) {
        return invoiceProduct.getPrice().multiply(BigDecimal.valueOf(invoiceProduct.getQuantity()));
    }

    @Override
    public void updateQuantityInStock(Long id) {

    }

    @Override
    public void updateRemainingQuantityUponApproval(Long id) {

    }

    @Override
    public void calculateProfitLoss(Long id) {

    }

    @Override
    public void lowQuantityAlert(Long id) {

    }
}
