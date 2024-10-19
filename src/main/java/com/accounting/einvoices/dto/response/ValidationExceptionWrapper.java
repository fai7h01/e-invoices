package com.accounting.einvoices.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationExceptionWrapper {

    private String errorField;
    private Object rejectedValue;
    private String reason;

}
