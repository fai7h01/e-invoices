package com.accounting.einvoices.enums;

import lombok.Getter;

@Getter
public enum UserStatus {

    ACTIVE("Active"), INACTIVE("Inactive");

    private final String val;

    UserStatus(String val) {
        this.val = val;
    }

}
