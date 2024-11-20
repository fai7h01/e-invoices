package com.accounting.einvoices.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;

public class BigDecimalUtil {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static BigDecimal format(BigDecimal value) {
        if (value == null)
            throw new NoSuchElementException("Value is null, cannot be formatted.");
        return value.setScale(SCALE, ROUNDING_MODE);
    }

}
