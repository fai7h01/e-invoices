package com.accounting.einvoices.dto.response.currencyExchange;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyExchangeDTO {


    @JsonProperty("USD")
    private BigDecimal usd;
    @JsonProperty("AUD")
    private BigDecimal aud;
    @JsonProperty("BGN")
    private BigDecimal bgn;
    @JsonProperty("CAD")
    private BigDecimal cad;
    @JsonProperty("CHF")
    private BigDecimal chf;
    @JsonProperty("CNY")
    private BigDecimal cny;
    @JsonProperty("EGP")
    private BigDecimal egp;
    @JsonProperty("EUR")
    private BigDecimal eur;
    @JsonProperty("GBP")
    private BigDecimal gbp;
    @JsonProperty("GEL")
    private BigDecimal gel;

}
