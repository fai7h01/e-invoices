package com.accounting.einvoices.enums;

import lombok.Getter;

@Getter
public enum Status {

    ACTIVE("Active"), INACTIVE("Inactive");

    private final String val;

    Status(String val) {
        this.val = val;
    }

}
