package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;
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

    @NotBlank
    @Email
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String password;
    @NotNull
    private String confirmPassword;
    @NotBlank
    @Size(max = 15, min = 2)
    private String firstName;
    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;
    @NotNull
    private LocalDate dateOfEmployment;
    private boolean enabled;
    @NotNull
    private UserStatus userStatus = UserStatus.Inactive;
    @NotNull
    private CompanyDTO company;
    @NotNull
    private RoleDTO role;


}
