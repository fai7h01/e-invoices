package com.accounting.einvoices.enums;

import lombok.Getter;

@Getter
public enum Currency {

    USD("US Dollar"), EUR("Euro"), GEL("Lari");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

}
