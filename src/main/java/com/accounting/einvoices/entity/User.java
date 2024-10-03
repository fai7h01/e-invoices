package com.accounting.einvoices.entity;

import com.accounting.einvoices.dto.LocationDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {


    private String firstName;
    private String lastName;
    private LocationDTO location;
    private String password;
    private String phone;

}
