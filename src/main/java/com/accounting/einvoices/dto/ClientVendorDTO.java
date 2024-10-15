package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.ClientVendorType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClientVendorDTO {

    private Long id;

    private String name;

    private String phone;

    private String website;

    private String email;

    private ClientVendorType clientVendorType;

    private AddressDTO address;

    private CompanyDTO company;

    @JsonIgnore
    private boolean hasInvoice;

}
