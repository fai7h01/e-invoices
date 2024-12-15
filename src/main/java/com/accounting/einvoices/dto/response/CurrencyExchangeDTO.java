package com.accounting.einvoices.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyExchangeDTO {


    @JsonProperty("USD")
    private Double usd;
    @JsonProperty("AUD")
    private Double aud;
    @JsonProperty("BGN")
    private Double bgn;
    @JsonProperty("CAD")
    private Double cad;
    @JsonProperty("CHF")
    private Double chf;
    @JsonProperty("CNY")
    private Double cny;
    @JsonProperty("EGP")
    private Double egp;
    @JsonProperty("EUR")
    private Double eur;
    @JsonProperty("GBP")
    private Double gbp;
    @JsonProperty("GEL")
    private Double gel;

}
