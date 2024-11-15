package com.accounting.einvoices.exception;

public class ClientCannotBeDeletedException extends RuntimeException{

    public ClientCannotBeDeletedException(String message) {
        super(message);
    }
}
