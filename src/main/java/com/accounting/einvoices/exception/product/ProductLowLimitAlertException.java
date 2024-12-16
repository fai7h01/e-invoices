package com.accounting.einvoices.exception.product;

public class ProductLowLimitAlertException extends RuntimeException{

    public ProductLowLimitAlertException(String message){
        super(message);
    }
}
