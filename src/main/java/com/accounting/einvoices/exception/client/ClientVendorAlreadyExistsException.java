package com.accounting.einvoices.exception.client;

public class ClientVendorAlreadyExistsException extends RuntimeException{

    public ClientVendorAlreadyExistsException(String message){
        super(message);
    }
}
