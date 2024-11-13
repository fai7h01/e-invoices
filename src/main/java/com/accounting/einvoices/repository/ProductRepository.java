package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryCompanyId(Long id);

    Optional<Product> findByNameIgnoreCase(String name);

}
