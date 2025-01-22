package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface InvoiceService {

    List<InvoiceDTO> findAllByLoggedInUser();

    List<InvoiceDTO> findAllByStatus(InvoiceStatus invoiceStatus);

    InvoiceDTO findById(Long id);

    InvoiceDTO findByInvNo(String invNo);

    InvoiceDTO generateInvoice();

    InvoiceDTO save(InvoiceDTO invoice);

    InvoiceDTO update(Long id, InvoiceDTO invoice);

    void delete(Long id);

    void setPriceTaxTotal(InvoiceDTO invoice);

    InvoiceDTO approve(String invNo);

    Map<Currency, List<InvoiceDTO>> findAllByAcceptDate(int year, int startMonth, int endMonth);

    List<InvoiceDTO> findAllByClientId(Long id);

    List<InvoiceDTO> recentlyApprovedInvoices();



}
