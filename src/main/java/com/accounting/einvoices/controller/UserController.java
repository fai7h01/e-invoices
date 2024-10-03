package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> findById(@PathVariable("id") Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(ResponseWrapper.builder().success(true).code(HttpStatus.OK.value())
                .message("User is successfully retrieved.")
                .data(user).build());
    }

    @PostMapping()
    public ResponseEntity<ResponseWrapper> saveUser(@RequestBody UserDTO user) {
        UserDTO saved = userService.save(user);
        return ResponseEntity.ok(ResponseWrapper.builder().success(true).code(HttpStatus.OK.value())
                .message("User is successfully created.")
                .data(saved).build());
    }


}
