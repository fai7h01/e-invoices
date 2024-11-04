package com.accounting.einvoices.service;

import com.accounting.einvoices.dto.RoleDTO;

public interface RoleService {

    RoleDTO findById(Long id);
    RoleDTO findByDescription(String desc);
}
