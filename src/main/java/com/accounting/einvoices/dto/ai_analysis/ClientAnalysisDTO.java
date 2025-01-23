package com.accounting.einvoices.dto.ai_analysis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientAnalysisDTO {

    private String clientName;
    private int soldProductsNumber;
    private int overDueInvoicesNumber;
    private BigDecimal totalSold;

}
