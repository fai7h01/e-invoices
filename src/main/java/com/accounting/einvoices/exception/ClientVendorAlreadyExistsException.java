package com.accounting.einvoices.exception;

public class ClientVendorAlreadyExistsException extends RuntimeException{

    public ClientVendorAlreadyExistsException(String message){
        super(message);
    }
}
