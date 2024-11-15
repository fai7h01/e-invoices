package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByCompanyId(Long id);

    List<Invoice> findAllByCompanyTitle(String companyTitle);

    List<Invoice> findAllByAcceptDateIs(LocalDate date);

    Optional<Invoice> findByInvoiceNoAndCompanyTitle(String invNo, String company);

    List<Invoice> findAllByClientVendorId(Long id);

    List<Invoice> findTop3ByCompanyIdAndInvoiceStatusOrderByDateOfIssueAsc(Long id, InvoiceStatus status);

}
