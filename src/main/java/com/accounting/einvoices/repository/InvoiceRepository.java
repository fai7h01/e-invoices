package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByCompanyId(Long id);

    List<Invoice> findAllByCompanyTitle(String companyTitle);

    @Query("SELECT i FROM Invoice i WHERE EXTRACT(YEAR FROM i.acceptDate) = :year AND EXTRACT(MONTH FROM i.acceptDate) BETWEEN :startMonth AND :endMonth " +
            "AND i.invoiceStatus = :status AND i.company.id = :companyId ORDER BY i.acceptDate")
    List<Invoice> findAllByYearAndMonthBetweenAndStatusAndCompany(@Param("year") int year,
                                                                  @Param("startMonth") int startMonth,
                                                                  @Param("endMonth") int endMonth,
                                                                  @Param("status") InvoiceStatus status,
                                                                  @Param("companyId") Long companyId);

    Optional<Invoice> findByInvoiceNoAndCompanyTitle(String invNo, String company);

    List<Invoice> findAllByClientVendorId(Long id);

    List<Invoice> findAllByCompanyIdAndInvoiceStatusOrderByDateOfIssueDesc(Long id, InvoiceStatus status);

}
