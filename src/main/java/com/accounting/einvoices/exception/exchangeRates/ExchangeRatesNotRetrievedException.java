package com.accounting.einvoices.exception.exchangeRates;

public class ExchangeRatesNotRetrievedException extends RuntimeException{
    public ExchangeRatesNotRetrievedException(String message) {
        super(message);
    }
}
