package com.accounting.einvoices.exception.company;

public class CompanyAlreadyExistsException extends RuntimeException{
    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}
