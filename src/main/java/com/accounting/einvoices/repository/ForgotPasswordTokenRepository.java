package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.ForgotPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordTokenRepository extends JpaRepository<ForgotPasswordToken, Long> {
}
