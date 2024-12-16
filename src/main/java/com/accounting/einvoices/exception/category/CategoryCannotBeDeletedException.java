package com.accounting.einvoices.exception.category;

public class CategoryCannotBeDeletedException extends RuntimeException{
    public CategoryCannotBeDeletedException(String message) {
        super(message);
    }
}
