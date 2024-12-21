package com.accounting.einvoices.enums;

import lombok.Getter;

@Getter
public enum ProductUnit {

    PCS("Pieces"),
    UNIT("Unit"),
    SET("Set"),
    PAIR("Pair"),
    BDL("Bundle"),
    BOX("Box"),
    CRT("Crate"),
    REEL("Reel"),
    KG("Kilogram"),
    G("Gram"),
    LBS("Pounds"),
    OZ("Ounce"),
    TN("Ton"),
    L("Litre"),
    ML("Millilitre"),
    GALLON("Gallon"),
    M("Meter"),
    CM("Centimeter"),
    MM("Millimeter"),
    FT("Foot"),
    INCH("Inch"),
    SQM("Square meter"),
    SQFT("Square foot"),
    CUBICMETER("Cubic Meter"),
    CUBICFOOT("Cubic Foot"),
    BTL("Bottle"),
    CAN("Can"),
    ROLL("Roll"),
    SHEET("Sheet"),
    LITER("Liter"),
    PACK("Pack"),
    KGPK("Kilo/Pack"),
    MPK("Meter/Pack");


    private final String value;

    ProductUnit(String value) {
        this.value = value;
    }
}
