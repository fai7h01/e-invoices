package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String phone;
    private String website;
    @NotBlank
    private String email;
    @NotNull
    private AddressDTO address;
    private Boolean hasWooCommerce;
    private boolean subscribed;
}
