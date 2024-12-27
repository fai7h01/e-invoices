package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByEmailAndToken(String email, String token);

}
