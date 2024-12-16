package com.accounting.einvoices.exception.category;

public class CategoryAlreadyExistsException extends RuntimeException{

    public CategoryAlreadyExistsException(String message){
        super(message);
    }
}
