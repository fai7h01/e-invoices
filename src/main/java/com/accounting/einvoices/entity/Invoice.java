package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
@Where(clause = "is_deleted = false")
public class Invoice extends BaseEntity {

    private String invoiceNo;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    private LocalDateTime dateOfIssue;

    private LocalDateTime dueDate;

    private LocalDateTime acceptDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String paymentTerms;

    private String notes;

    private String signature;

    @ManyToOne
    @JoinColumn(name = "client_vendor_id")
    private ClientVendor clientVendor;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
