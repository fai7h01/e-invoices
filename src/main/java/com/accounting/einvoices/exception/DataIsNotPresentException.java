package com.accounting.einvoices.exception;

public class DataIsNotPresentException extends RuntimeException{
    public DataIsNotPresentException(String message) {
        super(message);
    }
}
