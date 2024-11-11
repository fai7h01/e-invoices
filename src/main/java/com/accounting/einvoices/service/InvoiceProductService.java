package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.InvoiceProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceProductService {

    List<InvoiceProductDTO> findAllByInvoiceId(Long id);

    InvoiceProductDTO save(Long invoiceId, InvoiceProductDTO invoiceProduct);

    void delete(Long id);

    BigDecimal getTotalWithTax(InvoiceProductDTO invoiceProduct);

    BigDecimal getTotalWithoutTax(InvoiceProductDTO invoiceProduct);

    void updateQuantityInStock(Long id);

    void calculateProfitLoss(Long id);

    void lowQuantityAlert(Long id);

    List<InvoiceProductDTO> findAllByInvoiceIdAndCalculateTotalPrice(Long id);

}
