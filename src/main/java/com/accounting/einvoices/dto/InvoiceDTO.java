package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class InvoiceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String invoiceNo;
    @NotNull
    private InvoiceStatus invoiceStatus = InvoiceStatus.AWAITING_APPROVAL;
    @NotNull
    private LocalDateTime dateOfIssue;
    @NotNull
    private LocalDateTime dueDate;
    @NotNull
    private LocalDateTime acceptDate;
    @NotBlank
    private String paymentTerms;
    @NotBlank
    private String notes;
    private String signature;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompanyDTO company;
    @NotNull
    private ClientVendorDTO clientVendor;
    private String attachmentKey;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Currency currency;
    @NotNull
    private BigDecimal tax;
    @NotNull
    private BigDecimal total;

}
