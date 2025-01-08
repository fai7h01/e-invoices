package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.service.KeycloakService;
import com.accounting.einvoices.service.StorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Value("${application-bucket-name}")
    private String bucket;

    private final AmazonS3 s3Client;
    private final KeycloakService keycloakService;

    public StorageServiceImpl(@Qualifier("generateS3Client") AmazonS3 s3Client, KeycloakService keycloakService) {
        this.s3Client = s3Client;
        this.keycloakService = keycloakService;
    }


    @Override
    public void uploadFile(MultipartFile file, String key) {
        File converted = convertMultiPartFileToFile(file);
        s3Client.putObject(new PutObjectRequest(bucket, key, converted));
        converted.delete();
        log.info("File uploaded: {}", key);
    }

    @Override
    public void deleteFile(String name) {
        s3Client.deleteObject(bucket, name);
        log.info("{} is removed.", name);
    }

    @Override
    public ByteArrayResource downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucket, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return new ByteArrayResource(content);
        } catch (IOException e) {
            log.error("Error downloading file from bucket: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream os = new FileOutputStream(convertedFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipart file to file: {}", e.getMessage());
        }
        return convertedFile;
    }

    private UserDTO getLoggedInUser() {
        return keycloakService.getLoggedInUser();
    }
}
