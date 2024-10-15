package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findByUsername(String username);

    UserDTO findById(Long id);

    UserDTO create(UserDTO user);

    UserDTO update(UserDTO user);

    void delete(Long id);




}
