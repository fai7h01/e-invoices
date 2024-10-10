package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
