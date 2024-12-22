package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.CompletableFuture;

public interface KeycloakService {

    void userCreate(UserDTO dto);
    void userUpdate(UserDTO dto);
    void userDelete(String username);
    void emailVerification(String userId);
    CompletableFuture<Boolean> isEmailVerified(UserDTO dto);
    UserDTO getLoggedInUser();

}
