package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.enums.InvoiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String invoiceNo;

    private InvoiceStatus invoiceStatus = InvoiceStatus.AWAITING_APPROVAL;

    private InvoiceType invoiceType;

    private LocalDateTime dateOfIssue;

    private LocalDateTime dueDate;

    private String paymentTerms;

    private String notes;

    private String signature;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompanyDTO company;

    private ClientVendorDTO clientVendor;

    private BigDecimal price;

    private BigDecimal tax;

    private BigDecimal total;

}
