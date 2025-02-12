package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.ForgotPasswordDTO;
import com.accounting.einvoices.dto.RoleDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.User;
import com.accounting.einvoices.enums.UserStatus;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final RoleService roleService;
    private final CompanyService companyService;
    private final KeycloakService keycloakService;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, RoleService roleService, @Lazy CompanyService companyService,
                           @Lazy KeycloakService keycloakService) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.roleService = roleService;
        this.companyService = companyService;
        this.keycloakService = keycloakService;
    }

    @Override
    public List<UserDTO> findAll() {
        var loggedInUserDto = keycloakService.getLoggedInUser();
        List<User> users = userRepository.findAllByCompanyId(loggedInUserDto.getCompany().getId());
        return users.stream()
                .filter(user -> !user.getId().equals(loggedInUserDto.getId()))
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        var foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        return mapperUtil.convert(foundUser, new UserDTO());
    }

    @Override
    public UserDTO findById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        return mapperUtil.convert(user, new UserDTO( ));
    }

    @Override
    public UserDTO save(UserDTO dto) {
        Optional<User> found = userRepository.findByUsername(dto.getUsername());
        if (found.isPresent()) throw new UserAlreadyExistsException(dto.getUsername() + " is already exists in a system.");

        if (dto.getRole() == null) {
            RoleDTO admin = roleService.findById(1L);
            dto.setRole(admin);
        } else {
            RoleDTO foundRole = roleService.findByDescription(dto.getRole().getDescription());
            dto.getRole().setId(foundRole.getId());
        }

        if (dto.getCompany() == null) {
            CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
            dto.setCompany(loggedInCompany);
        } else {
            CompanyDTO company = dto.getCompany();
            CompanyDTO savedCompany = companyService.save(company);
            dto.getCompany().setId(savedCompany.getId());
        }

        var convertedUser = mapperUtil.convert(dto, new User());
        var savedUser = userRepository.save(convertedUser);
        log.info("\n\n>>User saved in database!");
        keycloakService.userCreate(mapperUtil.convert(savedUser, new UserDTO()));
        return mapperUtil.convert(savedUser, new UserDTO());
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        var foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        dto.setId(id);
        dto.getCompany().setId(foundUser.getCompany().getId());
        dto.setEnabled(true);

        var userToUpdate = mapperUtil.convert(dto, new User());
        var updatedUser = userRepository.save(userToUpdate);
        keycloakService.userUpdate(mapperUtil.convert(updatedUser, new UserDTO()));
        return mapperUtil.convert(updatedUser, new UserDTO());
    }

    @Override
    public void delete(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()){
            var user = foundUser.get();
            keycloakService.userDelete(user.getUsername());
            user.setUsername(user.getId() + "-" + user.getUsername());
            user.setIsDeleted(true);
            userRepository.save(user);
        }
    }


    @Override
    public void resetPassword(String username, ForgotPasswordDTO forgotPasswordDTO) {
        var dto = this.findByUsername(username);
        dto.setPassword(forgotPasswordDTO.getPassword());
        keycloakService.userUpdate(dto);
        userRepository.save(mapperUtil.convert(dto, new User()));
    }

    @Override
    public void updateStatus(String username) {
        var found = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        found.setUserStatus(UserStatus.Active);
        userRepository.save(found);
        keycloakService.verifyUser(found.getUsername());
    }

    @Override
    public boolean checkUserStatus(String username) {
        var foundUser = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
        return foundUser.getUserStatus().equals(UserStatus.Active);
    }


}
