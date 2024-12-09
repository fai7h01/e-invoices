package com.accounting.einvoices.entity;

import com.accounting.einvoices.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoice_products")
@Where(clause = "is_deleted = false")
public class InvoiceProduct extends BaseEntity {

    private String description;
    private int quantity;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal tax;
    private BigDecimal total;
    private BigDecimal profitLoss;
    private int remainingQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
