package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonIgnore
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dateOfEmployment;
    private boolean enabled;
    private Status status = Status.INACTIVE;
    private CompanyDTO company;
    private RoleDTO role;


}
