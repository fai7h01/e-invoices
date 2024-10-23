package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
