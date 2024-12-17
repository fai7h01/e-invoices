package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;


public interface KeycloakService {

    void userCreate(UserDTO dto);
    void userUpdate(UserDTO dto);
    void userDelete(String username);
    UserDTO getLoggedInUser();

}
