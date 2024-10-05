package com.accounting.einvoices.dto;

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

    private String name;
    private String industry;
    private String description;
    private AddressDTO location;
    private Integer estimatedRevenue;
}
