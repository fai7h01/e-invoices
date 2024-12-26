package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.KeycloakService;
import com.accounting.einvoices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/register")
@Tag(name = "Register Controller", description = "User Register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Register User")
    public ResponseEntity<ResponseWrapper> userRegister(@RequestBody UserDTO user) {
        UserDTO saved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User is successfully created.")
                .data(saved).build());
    }

    @GetMapping("/isEmailVerified/{username}")
    public ResponseEntity<ResponseWrapper> isEmailVerified(@PathVariable("username") String username) {
        boolean emailVerified = userService.isEmailVerified(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User email verified status.")
                .data(emailVerified).build());
    }
}
