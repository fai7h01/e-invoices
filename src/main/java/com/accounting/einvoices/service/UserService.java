package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.ForgotPasswordDTO;
import com.accounting.einvoices.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findByUsername(String username);

    UserDTO findById(Long id);

    UserDTO save(UserDTO user);

    UserDTO update(Long id, UserDTO user);

    void delete(Long id);

    boolean isEmailVerified(String username);

    void sendEmailVerification(String username);

    void resetPassword(String username, ForgotPasswordDTO forgotPasswordDTO);

    void updateStatus(UserDTO user);



}
