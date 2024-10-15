package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.Gender;
import com.accounting.einvoices.enums.Status;
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
    private String position;
    private boolean enabled;

    private LocalDate dateOfEmployment;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Company company;
}
