package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByCompanyId(Long id);

}
