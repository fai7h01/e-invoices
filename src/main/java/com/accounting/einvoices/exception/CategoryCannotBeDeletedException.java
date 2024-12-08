package com.accounting.einvoices.exception;

public class CategoryCannotBeDeletedException extends RuntimeException{
    public CategoryCannotBeDeletedException(String message) {
        super(message);
    }
}
