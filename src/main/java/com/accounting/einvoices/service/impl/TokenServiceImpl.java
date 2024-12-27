package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.entity.ForgotPasswordToken;
import com.accounting.einvoices.entity.VerificationToken;
import com.accounting.einvoices.exception.token.TokenNotFoundException;
import com.accounting.einvoices.exception.token.TokenNotValidException;
import com.accounting.einvoices.repository.ForgotPasswordTokenRepository;
import com.accounting.einvoices.repository.VerificationTokenRepository;
import com.accounting.einvoices.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final ForgotPasswordTokenRepository forgotPasswordTokenRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public TokenServiceImpl(ForgotPasswordTokenRepository forgotPasswordTokenRepository, VerificationTokenRepository verificationTokenRepository) {
        this.forgotPasswordTokenRepository = forgotPasswordTokenRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public void confirmForgotPasswordToken(String email, String token) {

        ForgotPasswordToken foundToken = forgotPasswordTokenRepository.findByEmailAndToken(email, token)
                .orElseThrow(() -> new TokenNotFoundException("Token not found."));

        if (!foundToken.isValid()) {
            throw new TokenNotValidException("Token is not valid.");
        }

        deleteForgotPasswordToken(foundToken);
    }

    @Override
    public void deleteForgotPasswordToken(ForgotPasswordToken token) {
        token.setIsDeleted(true);
        forgotPasswordTokenRepository.save(token);
    }

    @Override
    public void confirmVerificationPasswordToken(String email, String token) {

        VerificationToken foundToken = verificationTokenRepository.findByEmailAndToken(email, token)
                .orElseThrow(() -> new TokenNotFoundException("Token not found."));

        if (!foundToken.isValid()) {
            throw new TokenNotValidException("Token is not valid.");
        }

        deleteVerificationPasswordToken(foundToken);
    }

    @Override
    public void deleteVerificationPasswordToken(VerificationToken token) {
        token.setIsDeleted(true);
        verificationTokenRepository.save(token);
    }
}
