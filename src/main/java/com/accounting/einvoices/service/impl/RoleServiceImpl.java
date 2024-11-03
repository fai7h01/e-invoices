package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.RoleDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.Role;
import com.accounting.einvoices.repository.RoleRepository;
import com.accounting.einvoices.service.RoleService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Role not found."));
        return mapperUtil.convert(role, new RoleDTO());
    }

    @Override
    public void setAdmin(UserDTO user) {
        RoleDTO admin = findById(1L);
        user.setRole(admin);
    }

    @Override
    public void setManager(UserDTO user) {
        RoleDTO manager = findById(2L);
        user.setRole(manager);
    }

    @Override
    public void setEmployee(UserDTO user) {
        RoleDTO employee = findById(3L);
        user.setRole(employee);
    }
}
