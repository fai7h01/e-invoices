package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/company")
@Tag(name = "Company Controller", description = "Company CRUD Operations")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper> updateCompany(@PathVariable("id") Long id, @RequestBody @Valid CompanyDTO company) {
        CompanyDTO updated = companyService.update(id, company);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Company is successfully updated.")
                .data(updated).build());
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getLoggedInCompany() {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Company is successfully updated.")
                .data(loggedInCompany).build());
    }
}
