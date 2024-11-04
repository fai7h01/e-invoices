package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByDescription(String desc);

}
