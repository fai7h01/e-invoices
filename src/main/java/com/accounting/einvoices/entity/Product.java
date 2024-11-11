package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.ProductStatus;
import com.accounting.einvoices.enums.ProductUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
@Entity
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity {

    private String name;

    private String description;

    private int quantityInStock;

    private int lowLimitAlert;

    private LocalDate createdAt;

    private BigDecimal price;

    private boolean ingested;

    @Enumerated(EnumType.STRING)
    private ProductUnit productUnit;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


}
