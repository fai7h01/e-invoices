package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.enums.InvoiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private Long id;

    private String invoiceNo;

    private InvoiceStatus invoiceStatus = InvoiceStatus.AWAITING_APPROVAL;

    private InvoiceType invoiceType;

    private LocalDateTime dateOfIssue;

    private LocalDateTime dueDate;

    private CompanyDTO company;

    private ClientVendorDto clientVendor;

    private BigDecimal price;

    private BigDecimal tax;

    private BigDecimal total;


}
