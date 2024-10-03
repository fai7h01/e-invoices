package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.response.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> findById(@PathVariable("id") Long id) {
        return
    }


}
