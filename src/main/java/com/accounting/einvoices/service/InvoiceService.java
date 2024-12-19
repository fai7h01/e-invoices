package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface InvoiceService {

    List<InvoiceDTO> findAllByLoggedInUser();

    List<InvoiceDTO> findAllByCompanyTitle(String company);

    InvoiceDTO findById(Long id);

    InvoiceDTO findByInvNoAndCompanyTitle(String invNo, String company);

    InvoiceDTO generateInvoice();

    InvoiceDTO save(InvoiceDTO invoice);

    InvoiceDTO update(Long id, InvoiceDTO invoice);

    void delete(Long id);

    void setPriceTaxTotal(InvoiceDTO invoice);

    void approve(Long id);

    InvoiceDTO approve(String invNo, String companyTitle);

    Map<Currency, List<InvoiceDTO>> findAllByAcceptDate(int year, int startMonth, int endMonth);

    List<InvoiceDTO> findAllByClientId(Long id);

    List<InvoiceDTO> recentlyApprovedInvoices();


}
