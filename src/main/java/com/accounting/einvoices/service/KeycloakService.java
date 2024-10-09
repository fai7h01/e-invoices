package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;

import javax.ws.rs.core.Response;

public interface KeycloakService {

    Response userCreate(UserDTO dto);
    void delete(String username);
    UserDTO getLoggedInUser();

}
