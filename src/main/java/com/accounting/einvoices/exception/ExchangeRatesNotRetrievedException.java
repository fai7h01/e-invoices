package com.accounting.einvoices.exception;

public class ExchangeRatesNotRetrievedException extends RuntimeException{
    public ExchangeRatesNotRetrievedException(String message) {
        super(message);
    }
}
