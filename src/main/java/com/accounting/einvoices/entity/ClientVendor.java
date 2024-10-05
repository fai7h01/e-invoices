package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.ClientVendorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "clients_vendors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLRestriction("is_deleted is false")
public class ClientVendor extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String clientVendorName;

    @Column(nullable = false)
    private String phone;

    private String website;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClientVendorType clientVendorType;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
