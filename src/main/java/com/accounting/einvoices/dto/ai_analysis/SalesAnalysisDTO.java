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
public class SalesAnalysisDTO {

    private BigDecimal totalSales;
    private BigDecimal totalCost;
    private BigDecimal profitLoss;
    private String timeframe;


}
