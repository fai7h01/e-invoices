package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.RoleDTO;
import com.accounting.einvoices.dto.UserDTO;

public interface RoleService {

    RoleDTO findById(Long id);
    void setAdmin(UserDTO user);
    void setManager(UserDTO user);
    void setEmployee(UserDTO user);
}
