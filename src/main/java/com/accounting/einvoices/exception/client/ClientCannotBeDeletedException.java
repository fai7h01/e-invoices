package com.accounting.einvoices.exception.client;

public class ClientCannotBeDeletedException extends RuntimeException{

    public ClientCannotBeDeletedException(String message) {
        super(message);
    }
}
