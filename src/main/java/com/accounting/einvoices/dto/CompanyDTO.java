package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long id;

    private String title;
    private String phone;
    private String website;
    private AddressDTO address;
    //private Integer estimatedRevenue;
}
