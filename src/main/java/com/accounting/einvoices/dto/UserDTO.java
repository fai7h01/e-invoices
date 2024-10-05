package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.Status;
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
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Status status = Status.NONACTIVE;
    private CompanyDTO companyDTO;
    private RoleDTO role;


}
