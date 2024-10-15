package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.ClientVendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/clientVendor")
public class ClientVendorController {

    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createClientVendor(@RequestBody ClientVendorDTO clientVendor) {
        ClientVendorDTO saved = clientVendorService.create(clientVendor);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Client/Vendor is successfully created.")
                .data(saved).build());
    }


    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllClientVendors() {
        List<ClientVendorDTO> list = clientVendorService.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Client/Vendor list is successfully retrieved.")
                .data(list).build());
    }
}
