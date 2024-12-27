package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.entity.ForgotPasswordToken;
import com.accounting.einvoices.exception.token.TokenNotFoundException;
import com.accounting.einvoices.exception.token.TokenNotValidException;
import com.accounting.einvoices.repository.TokenRepository;
import com.accounting.einvoices.service.TokenService;
import org.springframework.stereotype.Service;


@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void confirmForgotPasswordToken(String email, String token) {

        ForgotPasswordToken foundToken = tokenRepository.findByEmailAndToken(email, token)
                .orElseThrow(() -> new TokenNotFoundException("Token not found."));

        if (!foundToken.isValid()) {
            throw new TokenNotValidException("Token is not valid.");
        }

        deleteForgotPasswordToken(foundToken);
    }

    @Override
    public void deleteForgotPasswordToken(ForgotPasswordToken token) {
        tokenRepository.delete(token);
    }
}
