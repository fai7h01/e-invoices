package com.accounting.einvoices.dto.ai_analysis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientAnalysisDTO {

    private int totalClientNumber;
    private List<ClientData> clients;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ClientData {

        private String clientName;
        private int totalInvoices;
        private BigDecimal averageInvoiceValue;
        private int paidInvoices;
        private int OverDueInvoices;

    }
}
