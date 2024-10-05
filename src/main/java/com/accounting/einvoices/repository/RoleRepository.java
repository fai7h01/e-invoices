package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
