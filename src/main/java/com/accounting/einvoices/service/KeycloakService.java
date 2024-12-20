package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KeycloakService {

    void userCreate(UserDTO dto);
    void userUpdate(UserDTO dto);
    void userDelete(String username);
    void emailVerification(String userId);
    boolean isEmailVerified(UserDTO dto);
    UserDTO getLoggedInUser();

}
