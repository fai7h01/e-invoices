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

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User Controller", description = "User CRUD Operations")
public class UserController {

    private final UserService userService;
    private final KeycloakService keycloakService;

    public UserController(UserService userService, KeycloakService keycloakService) {
        this.userService = userService;
        this.keycloakService = keycloakService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create/Register User")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user) {
        UserDTO saved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("User is successfully created.")
                .data(saved).build());
    }


    @GetMapping("/list")
    @Operation(summary = "Get All Users")
    public ResponseEntity<ResponseWrapper> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("User list is successfully retrieved.")
                .data(users).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user){
        UserDTO updated = userService.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("User is successfully updated.")
                .data(updated).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("loggedInUser")
    public ResponseEntity<ResponseWrapper> getLoggedInUser() {
        UserDTO loggedInUser = keycloakService.getLoggedInUser();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Logged in user is successfully retrieved.")
                .data(loggedInUser).build());
    }


}
