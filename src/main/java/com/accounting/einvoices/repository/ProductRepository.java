package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Product;
import com.accounting.einvoices.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryCompanyId(Long id);

    Optional<Product> findByNameIgnoreCase(String name);

    List<Product> findAllByCategoryId(Long id);

    @Query("SELECT p FROM Product p WHERE EXTRACT(YEAR FROM p.createdAt) = :year AND EXTRACT(MONTH FROM p.createdAt) " +
            "BETWEEN :startMonth AND :endMonth AND p.currency = :currency")
    List<Product> findAllByCreatedDateBetweenMonths(@Param("year") int year,
                                                    @Param("startMonth") int startMonth,
                                                    @Param("endMonth") int endMonth,
                                                    @Param("currency") Currency currency);
}
