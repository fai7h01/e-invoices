package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.ProductUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
@Entity
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {

    private String name;
    private int quantityInStock;
    private int lowLimitAlert;

    @Enumerated(EnumType.STRING)
    private ProductUnit productUnit;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


}
