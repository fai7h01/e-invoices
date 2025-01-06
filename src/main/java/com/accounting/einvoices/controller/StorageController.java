package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.StorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/bucket")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseWrapper> uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.uploadFile(file);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("File is uploaded.")
                .build());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) {
        ByteArrayResource resource = storageService.downloadFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(fileName)
                .build());
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource.getByteArray());
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<ResponseWrapper> deleteFile(@PathVariable("fileName") String fileName) {
        storageService.deleteFile(fileName);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message(fileName + " is deleted.")
                .build());
    }

}
