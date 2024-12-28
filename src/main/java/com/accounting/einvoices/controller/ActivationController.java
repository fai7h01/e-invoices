package com.accounting.einvoices.controller;

import com.accounting.einvoices.service.TokenService;
import com.accounting.einvoices.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/activate")
public class ActivationController {

    private final UserService userService;
    private final TokenService tokenService;

    public ActivationController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public String userActivation(@RequestParam("email") String email, @RequestParam("token") String token) {
        tokenService.confirmVerificationPasswordToken(email, token);
        userService.updateStatus(email);
        return "EmailConfirmation";
    }

}
