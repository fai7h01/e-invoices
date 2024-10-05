package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.entity.User;
import com.accounting.einvoices.enums.Status;
import com.accounting.einvoices.exceptiojn.UserAlreadyExistsException;
import com.accounting.einvoices.exceptiojn.UserNotFoundException;
import com.accounting.einvoices.repository.UserRepository;
import com.accounting.einvoices.service.UserService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> findAll() {
        return List.of();
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
        if (checkIfUserExists(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername() + " is already exists in a system.");
        }
        User saved = userRepository.save(mapperUtil.convert(user, new User()));
        return mapperUtil.convert(saved, new UserDTO());
    }

    @Override
    public UserDTO register(UserDTO user) {
        if (checkIfUserExists(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername() + " is already exists in a system.");
        }
        User saved = userRepository.save(mapperUtil.convert(user, new User()));
        return mapperUtil.convert(saved, new UserDTO());
    }

    @Override
    public void logIn(UserDTO user) {

    }


    @Override
    public boolean checkIfUserExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

}
