package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.ClientVendorType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientVendorDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    private String website;
    @NotBlank
    private String email;
    @NotNull
    private ClientVendorType clientVendorType;
    @NotNull
    private AddressDTO address;
    @JsonIgnore
    private CompanyDTO company;

}
