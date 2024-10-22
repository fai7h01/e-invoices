package com.accounting.einvoices.exception;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException(String message){
        super(message);
    }
}
