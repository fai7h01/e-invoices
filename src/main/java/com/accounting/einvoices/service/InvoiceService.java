package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.InvoiceDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> findAllByLoggedInUser();

    List<InvoiceDTO> findAllByCompanyTitle(String company);

    InvoiceDTO findById(Long id);

    InvoiceDTO generateInvoice();

    InvoiceDTO save(InvoiceDTO invoice);

    InvoiceDTO update(Long id, InvoiceDTO invoice);

    void delete(Long id);

    void setPriceTaxAndTotal(InvoiceDTO invoice);

    void approve(Long id);

    //approve invoice using company and invNo
    InvoiceDTO approve(String invNo, String companyTitle);

    BigDecimal countTotalCost();

    BigDecimal countTotalSales();

    BigDecimal sumProfitLoss();

    List<InvoiceDTO> findAllByAcceptDate(LocalDate date);

    List<InvoiceDTO> findAllByClientId(Long id);
}
