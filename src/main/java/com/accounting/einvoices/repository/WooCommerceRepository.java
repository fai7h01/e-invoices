package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.WooCommerceCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WooCommerceRepository extends JpaRepository<WooCommerceCredentials, Long> {

    Optional<WooCommerceCredentials> findByCompanyTitle(String title);
    Optional<WooCommerceCredentials> findByCompanyId(Long title);
}
