package com.accounting.einvoices.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface StorageService {


    void uploadFile(MultipartFile file, String key);

    void deleteFile(String name);

    File convertMultiPartFileToFile(MultipartFile file);

    ByteArrayResource downloadFile(String fileName);


}
