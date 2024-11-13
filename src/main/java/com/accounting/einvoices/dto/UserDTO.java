package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dateOfEmployment;
    private boolean enabled;
    private UserStatus userStatus = UserStatus.INACTIVE;
    private CompanyDTO company;
    private RoleDTO role;


}
