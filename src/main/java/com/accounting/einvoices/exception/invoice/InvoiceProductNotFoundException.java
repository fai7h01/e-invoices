package com.accounting.einvoices.exception.invoice;

public class InvoiceProductNotFoundException extends RuntimeException{

    public InvoiceProductNotFoundException(String message){
        super(message);
    }
}
