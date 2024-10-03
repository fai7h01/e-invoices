package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO save(UserDTO user);

//    UserDTO update(Long id);
//
//    void delete(Long id);

}
