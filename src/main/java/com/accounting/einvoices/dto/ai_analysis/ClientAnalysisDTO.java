package com.accounting.einvoices.dto.ai_analysis;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientAnalysisDTO {

    private String name;
    private String phone;
    private String email;
    //private ClientVendorType clientVendorType;
    private String addressSummary;

}
