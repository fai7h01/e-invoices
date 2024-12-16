package com.accounting.einvoices.exception.product;

public class ProductCannotBeDeletedException extends RuntimeException{
    public ProductCannotBeDeletedException(String message) {
        super(message);
    }
}
