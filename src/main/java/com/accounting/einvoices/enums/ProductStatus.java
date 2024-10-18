package com.accounting.einvoices.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {

    ACTIVE("Active"), DRAFT("Draft");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }
}
