package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    List<UserDTO> findAllByIngested(boolean ingested);

    UserDTO findByUsername(String username);

    UserDTO findById(Long id);

    UserDTO save(UserDTO user);

    UserDTO update(Long id, UserDTO user);

    void delete(Long id);




}
