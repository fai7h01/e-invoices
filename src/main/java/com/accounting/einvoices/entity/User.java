package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Where(clause = "is_deleted = false")
public class User extends BaseEntity{

    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean enabled;
    private LocalDate dateOfEmployment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
