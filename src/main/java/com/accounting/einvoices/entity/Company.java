package com.accounting.einvoices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
@Table(name = "companies")
@SQLRestriction("is_deleted is false")
public class Company extends BaseEntity{

    private String name;
    private String industry;
    private String description;
    private Integer estimatedRevenue;

    @OneToOne
    private Location location;

}
