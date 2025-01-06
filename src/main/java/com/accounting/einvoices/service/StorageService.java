package com.accounting.einvoices.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {


    void uploadFile(MultipartFile file);

    void deleteFile(String name);

    ByteArrayResource downloadFile(String fileName);


}
