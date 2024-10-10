package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper> userRegister(@RequestBody UserDTO user) {
        UserDTO saved = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User is successfully registered.")
                .data(saved).build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> userCreate(@RequestBody UserDTO user) {
        UserDTO saved = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User is successfully created.")
                .data(saved).build());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User list is successfully registered.")
                .data(users).build());
    }

}
