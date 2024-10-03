package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {


    @Override
    public List<UserDTO> findAll() {
        return List.of();
    }

    @Override
    public UserDTO findById(Long id) {
        return null;
    }

    @Override
    public UserDTO save(UserDTO user) {
        return null;
    }
}
