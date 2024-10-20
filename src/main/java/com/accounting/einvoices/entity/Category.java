package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.CategoryIcon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Where(clause = "is_deleted = false")
public class Category extends BaseEntity {

    private String description;

    @ManyToOne
    private Company company;

    @Enumerated(EnumType.STRING)
    private CategoryIcon icon;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
