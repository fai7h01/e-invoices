package com.accounting.einvoices.exception;

public class FileCannotBeDownloadedException extends RuntimeException{
    public FileCannotBeDownloadedException(String message) {
        super(message);
    }
}
