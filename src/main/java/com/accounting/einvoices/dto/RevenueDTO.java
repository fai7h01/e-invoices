package com.accounting.einvoices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDTO {

    private Currency currency;
    private String estimated;

}
