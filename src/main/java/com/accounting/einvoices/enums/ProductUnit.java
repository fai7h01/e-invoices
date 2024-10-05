package com.accounting.einvoices.enums;

import lombok.Getter;

@Getter
public enum ProductUnit {

    LBS("Pounds"), GALLON("Gallon"), PCS("Pieces"), KG("Kilogram"), METER("Meter"), INCH("Inch"), FEET("Feet");

    private final String value;

    ProductUnit(String value) {
        this.value = value;
    }
}
