package com.accounting.einvoices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
@Where(clause = "is_deleted = false")
public class Subscription extends BaseEntity{


    @OneToOne
    private Company company;

    private String subscriptionId;
    private String planId;
    private String status;
    private String activationTime;
}
