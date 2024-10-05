package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findByUsername(String username);

    UserDTO findById(Long id);

    UserDTO create(UserDTO user);

    boolean checkIfUserExists(String username);


//    UserDTO update(Long id);
//
//    void delete(Long id);

}
