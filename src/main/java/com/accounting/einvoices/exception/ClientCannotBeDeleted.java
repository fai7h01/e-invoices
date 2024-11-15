package com.accounting.einvoices.exception;

public class ClientCannotBeDeleted extends RuntimeException{

    public ClientCannotBeDeleted(String message) {
        super(message);
    }
}
