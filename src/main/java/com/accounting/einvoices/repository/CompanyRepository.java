package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByTitle(String title);

}
