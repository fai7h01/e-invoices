package com.accounting.einvoices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String phone;
    private LocationDTO location;
    private CompanyDTO companyDTO;


}
