package com.accounting.einvoices.service;

import com.accounting.einvoices.entity.ForgotPasswordToken;

public interface TokenService {

    void confirmForgotPasswordToken(String email, String token);

    void deleteForgotPasswordToken(ForgotPasswordToken token);

}
