package com.accounting.einvoices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SQLRestriction("is_deleted is false")
public class User extends BaseEntity{


    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    //private Location location;
    private String password;
    private String phone;

}
