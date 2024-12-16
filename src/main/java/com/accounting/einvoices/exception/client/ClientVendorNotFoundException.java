package com.accounting.einvoices.exception.client;

public class ClientVendorNotFoundException extends RuntimeException{

    public ClientVendorNotFoundException(String message){
        super(message);
    }
}
