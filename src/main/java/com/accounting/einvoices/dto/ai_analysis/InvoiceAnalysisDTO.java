package com.accounting.einvoices.dto.ai_analysis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceAnalysisDTO {

    private int totalInvoicesNumber;
    private int totalApprovedInvoicesNumber;
    private int totalOverDueInvoicesNumber;
    private int totalPendingInvoicesNumber;

}
