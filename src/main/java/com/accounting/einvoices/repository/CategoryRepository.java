package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCompanyId(Long id);

    Optional<Category> findByDescriptionIgnoreCaseAndCompany_Id(String desc, Long id);
}
