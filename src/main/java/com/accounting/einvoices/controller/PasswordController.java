package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.ForgotPasswordDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.EmailService;
import com.accounting.einvoices.service.TokenService;
import com.accounting.einvoices.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/password")
@Tag(name = "Password Controller", description = "Password API")
public class PasswordController {

    private final EmailService emailService;
    private final UserService userService;
    private final TokenService tokenService;

    public PasswordController(EmailService emailService, UserService userService, TokenService tokenService) {
        this.emailService = emailService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("forgot-password")
    public ResponseEntity<ResponseWrapper> forgotPassword(@RequestParam("email") String emal) {
        emailService.sendForgotPasswordEmail(emal);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Forgot password mail was sent successfully")
                .build());
    }

    @PostMapping("/new-password")
    public ResponseEntity<ResponseWrapper> newPassword(@RequestParam("email") String email, @RequestParam("token") String token,
                                                              @RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        tokenService.confirmForgotPasswordToken(email, token);
        userService.resetPassword(email, forgotPasswordDTO);
        emailService.sendConfirmPasswordResetEmail(email);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Password has been changed successfully")
                .build());
    }
}
