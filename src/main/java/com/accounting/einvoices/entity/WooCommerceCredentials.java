package com.accounting.einvoices.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "woo_commerce_credentials")
public class WooCommerceCredentials extends BaseEntity{

    private String baseUrl;
    private String consumerKey;
    private String consumerSecret;

    @OneToOne
    private User user;
}
