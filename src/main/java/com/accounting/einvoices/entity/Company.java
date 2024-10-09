package com.accounting.einvoices.entity;

import jakarta.persistence.*;
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
@Table(name = "companies")
@SQLRestriction("is_deleted is false")
public class Company extends BaseEntity{

    private String title;
    private String phone;
    private String website;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

}
