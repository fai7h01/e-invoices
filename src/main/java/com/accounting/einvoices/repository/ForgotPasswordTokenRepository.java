package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.ForgotPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForgotPasswordTokenRepository extends JpaRepository<ForgotPasswordToken, Long> {

    Optional<ForgotPasswordToken> findByEmailAndToken(String email, String token);

}
