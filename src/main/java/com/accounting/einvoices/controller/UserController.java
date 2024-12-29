package com.accounting.einvoices.controller;

import com.accounting.einvoices.annotation.ExecutionTime;
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

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;
import java.util.List;

@Slf4j
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

    @ExecutionTime
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

    @GetMapping("/loggedInUser")
    public ResponseEntity<ResponseWrapper> getLoggedInUser() {
        UserDTO loggedInUser = keycloakService.getLoggedInUser();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Logged in user is successfully retrieved.")
                .data(loggedInUser).build());
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<ResponseWrapper> findByUsername(@PathVariable("username") String username) {
        UserDTO user = userService.findByUsername(username);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("User was found successfully.")
                .data(user).build());
    }

    @GetMapping("/checkUserStatus/{username}")
    public ResponseEntity<ResponseWrapper> checkUserStatus(@PathVariable("username") String username) {
        boolean status = userService.checkUserStatus(username);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("User Active status.")
                .data(status).build());
    }



}
