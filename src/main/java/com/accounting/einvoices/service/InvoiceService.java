package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.InvoiceDTO;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> findAll();

    List<InvoiceDTO> findAllByCompanyId(Long id);

    InvoiceDTO findById(Long id);

    InvoiceDTO generateInvoice();

    InvoiceDTO save(InvoiceDTO invoice);

    InvoiceDTO update(Long id, InvoiceDTO invoice);

    void delete(Long id);

    void setPriceTaxAndTotal(InvoiceDTO invoice);

    void approve(Long id);

    BigDecimal countTotalCost();

    BigDecimal countTotalSales();

    BigDecimal sumProfitLoss();

}
