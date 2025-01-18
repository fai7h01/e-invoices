package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.ClientVendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientVendor")
public class ClientVendorController {

    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createClientVendor(@RequestBody @Valid ClientVendorDTO clientVendor) {
        ClientVendorDTO saved = clientVendorService.save(clientVendor);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Client/Vendor is successfully created.")
                .data(saved).build());
    }


    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllClientVendors() {
        List<ClientVendorDTO> list = clientVendorService.findAll();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Client/Vendor list is successfully retrieved.")
                .data(list).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper> updateClientVendor(@PathVariable("id") Long id, @RequestBody @Valid ClientVendorDTO clientVendor) {
        ClientVendorDTO updated = clientVendorService.update(id, clientVendor);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Client/Vendor is successfully updated")
                .data(updated).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteClientVendor(@PathVariable("id") Long id) {
        clientVendorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
