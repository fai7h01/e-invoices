package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByCompanyIdOrderByInvoiceNo(Long id);

    Optional<Invoice> findByInvoiceNoAndCompanyId(String invNo, Long id);

    @Query("SELECT i FROM Invoice i WHERE EXTRACT(YEAR FROM i.acceptDate) = :year AND EXTRACT(MONTH FROM i.acceptDate) BETWEEN :startMonth AND :endMonth " +
            "AND i.invoiceStatus = :status AND i.company.id = :companyId ORDER BY i.acceptDate")
    List<Invoice> findAllByYearAndMonthBetweenAndStatusAndCompany(@Param("year") int year,
                                                                  @Param("startMonth") int startMonth,
                                                                  @Param("endMonth") int endMonth,
                                                                  @Param("status") InvoiceStatus status,
                                                                  @Param("companyId") Long companyId);

    List<Invoice> findAllByInvoiceStatusAndCompanyId(InvoiceStatus invoiceStatus, Long id);

    List<Invoice> findAllByClientVendorId(Long id);

    List<Invoice> findAllByCompanyIdAndInvoiceStatusOrderByDateOfIssueDesc(Long id, InvoiceStatus status);

}
