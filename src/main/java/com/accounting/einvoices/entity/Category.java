package com.accounting.einvoices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
@SQLRestriction("is_deleted is false")
public class Category extends BaseEntity {

    private String description;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
