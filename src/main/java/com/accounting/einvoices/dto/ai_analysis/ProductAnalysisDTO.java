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
public class ProductAnalysisDTO {

    private String mostSellingProduct;
    private String mostEarningProduct;
    private List<String> topFiveExpensiveProduct;
    private List<String> topFiveCheapestProduct;

}
