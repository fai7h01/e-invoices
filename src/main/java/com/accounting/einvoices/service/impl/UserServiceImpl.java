package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.RoleDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.User;
import com.accounting.einvoices.exception.user.UserAlreadyExistsException;
import com.accounting.einvoices.exception.user.UserNotFoundException;
import com.accounting.einvoices.repository.UserRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.KeycloakService;
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
    private final KeycloakService keycloakService;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, RoleService roleService, @Lazy CompanyService companyService, @Lazy KeycloakService keycloakService) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.roleService = roleService;
        this.companyService = companyService;
        this.keycloakService = keycloakService;
    }

    @Override
    public List<UserDTO> findAll() {
        UserDTO loggedInUser = keycloakService.getLoggedInUser();
        List<User> users = userRepository.findAllByCompanyId(loggedInUser.getCompany().getId());
        return users.stream()
                .filter(user -> !user.getId().equals(loggedInUser.getId()))
                .map(user -> mapperUtil.convert(user, new UserDTO())).toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        return mapperUtil.convert(foundUser, new UserDTO());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        return mapperUtil.convert(user, new UserDTO( ));
    }

    @Override
    public UserDTO save(UserDTO user) {
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        if (found.isPresent()) throw new UserAlreadyExistsException(user.getUsername() + " is already exists in a system.");

        if (user.getRole() == null) {
            RoleDTO admin = roleService.findById(1L);
            user.setRole(admin);
        } else {
            RoleDTO foundRole = roleService.findByDescription(user.getRole().getDescription());
            user.getRole().setId(foundRole.getId());
        }

        if (user.getCompany() == null) {
            CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
            user.setCompany(loggedInCompany);
        } else {
            CompanyDTO company = user.getCompany();
            CompanyDTO savedCompany = companyService.save(company);
            user.getCompany().setId(savedCompany.getId());
        }

        User convertedUser = mapperUtil.convert(user, new User());

        User saved = userRepository.save(convertedUser);
        log.info("\n\n>>User saved in database!");
        keycloakService.userCreate(mapperUtil.convert(saved, new UserDTO()));

        return mapperUtil.convert(saved, new UserDTO());
    }

    @Override
    public UserDTO update(Long id, UserDTO user) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        user.setId(id);
        user.getCompany().setId(foundUser.getCompany().getId());
        user.setEnabled(true);

        User userToUpdate = mapperUtil.convert(user, new User());
        User updatedUser = userRepository.save(userToUpdate);
        keycloakService.userUpdate(mapperUtil.convert(updatedUser, new UserDTO()));
        return mapperUtil.convert(updatedUser, new UserDTO());
    }

    @Override
    public void delete(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()){
            User user = foundUser.get();
            keycloakService.userDelete(user.getUsername());
            user.setUsername(user.getId() + "-" + user.getUsername());
            user.setIsDeleted(true);
            userRepository.save(user);
        }
    }



}
