package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.EmailService;
import com.accounting.einvoices.service.TokenService;
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
    private final EmailService emailService;
    private final TokenService tokenService;

    public RegisterController(UserService userService, EmailService emailService, TokenService tokenService) {
        this.userService = userService;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @Operation(summary = "Register User")
    public ResponseEntity<ResponseWrapper> userRegister(@RequestBody UserDTO user) {
        UserDTO saved = userService.save(user);
        emailService.sendVerificationEmail(user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User is successfully created. Please check email and click the button to confirm email.")
                .data(saved).build());
    }

    @PostMapping("/activate")
    public ResponseEntity<ResponseWrapper> userActivation(@RequestParam("email") String email, @RequestParam("token") String token) {
        tokenService.confirmVerificationPasswordToken(email, token);
        userService.updateStatus(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User is successfully activated.")
                .build());
    }


}
