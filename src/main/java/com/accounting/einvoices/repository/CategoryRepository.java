package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCompanyId(Long id);

}
