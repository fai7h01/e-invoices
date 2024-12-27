package com.accounting.einvoices.service;

import com.accounting.einvoices.entity.ForgotPasswordToken;
import com.accounting.einvoices.entity.VerificationToken;

public interface TokenService {

    void confirmForgotPasswordToken(String email, String token);

    void deleteForgotPasswordToken(ForgotPasswordToken token);

    void confirmVerificationPasswordToken(String email, String token);

    void deleteVerificationPasswordToken(VerificationToken token);
}
