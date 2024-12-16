package com.accounting.einvoices.exception.invoice;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException(String message){
        super(message);
    }
}
