package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.repository.UserRepository;
import com.accounting.einvoices.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    MapperUtil mapperUtil;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void should_find_user_by_username() {

        userService.findByUsername("lionelfusco@email.com");

        verify(userRepository).findByUsername("lionelfusco@email.com");

    }
}