package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCompanyId(Long id);

    Optional<Category> findByDescriptionIgnoreCaseAndCompany_Id(String desc, Long id);

    @Query("SELECT c FROM Category c WHERE c.products.size > 0 AND c.company.id = :company_id")
    List<Category> findAllByProductCountGreater(@Param("company_id") Long id);
}
