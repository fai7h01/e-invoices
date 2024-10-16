package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
