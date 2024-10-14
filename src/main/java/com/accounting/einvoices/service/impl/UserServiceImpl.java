package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.User;
import com.accounting.einvoices.exception.UserAlreadyExistsException;
import com.accounting.einvoices.exception.UserNotFoundException;
import com.accounting.einvoices.repository.UserRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.RoleService;
import com.accounting.einvoices.service.UserService;
import com.accounting.einvoices.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final RoleService roleService;
    private final CompanyService companyService;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, RoleService roleService, @Lazy CompanyService companyService) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.roleService = roleService;
        this.companyService = companyService;
    }

    @Override
    public List<UserDTO> findAll() {
        //getLoggedIn user, get company id, find based on company id.
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapperUtil.convert(user, new UserDTO())).toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("username ->>>>>>>>>>>>>>>>>>>>>>>>: {}", username);
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        return mapperUtil.convert(foundUser, new UserDTO());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        return mapperUtil.convert(user, new UserDTO( ));
    }

    @Override
    public UserDTO create(UserDTO user) {

        if (checkIfUserExists(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername() + " is already exists in a system.");
        }

        //get logged in company and set it to user
        if (user.getCompany() == null) user.setCompany(companyService.getByLoggedInUser());

        if (user.getRole() == null) roleService.setAdmin(user);

        User saved = userRepository.save(mapperUtil.convert(user, new User()));
        return mapperUtil.convert(saved, new UserDTO());
    }

    @Override
    public UserDTO update(UserDTO user) {
        UserDTO foundUser = findByUsername(user.getUsername());
        user.setId(foundUser.getId());
        User converted = mapperUtil.convert(user, new User());
        User saved = userRepository.save(converted);
        return mapperUtil.convert(saved, new UserDTO());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean checkIfUserExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }


}
