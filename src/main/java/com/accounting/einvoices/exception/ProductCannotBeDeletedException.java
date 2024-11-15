package com.accounting.einvoices.exception;

public class ProductCannotBeDeletedException extends RuntimeException{
    public ProductCannotBeDeletedException(String message) {
        super(message);
    }
}
