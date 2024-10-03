package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
