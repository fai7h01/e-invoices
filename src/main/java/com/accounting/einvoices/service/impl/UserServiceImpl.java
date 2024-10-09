package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.User;
import com.accounting.einvoices.exceptiojn.UserAlreadyExistsException;
import com.accounting.einvoices.exceptiojn.UserNotFoundException;
import com.accounting.einvoices.repository.UserRepository;
import com.accounting.einvoices.service.RoleService;
import com.accounting.einvoices.service.UserService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, RoleService roleService) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.roleService = roleService;
    }

    @Override
    public List<UserDTO> findAll() {
        //getLoggedIn user, get company id, find based on company id.
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapperUtil.convert(user, new UserDTO())).toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        return mapperUtil.convert(foundUser, new UserDTO());
    }

    @Override
    public UserDTO findById(Long id) {
        return null;
    }

    @Override
    public UserDTO create(UserDTO user) {

        //if logged in user != null, get company from logged in user and assign new user.

        if (checkIfUserExists(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername() + " is already exists in a system.");
        }

        if (user.getRole() == null) roleService.setAdmin(user);

        User saved = userRepository.save(mapperUtil.convert(user, new User()));
        return mapperUtil.convert(saved, new UserDTO());
    }

    @Override
    public boolean checkIfUserExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }


}
