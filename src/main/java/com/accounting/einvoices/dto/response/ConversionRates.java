
package com.accounting.einvoices.dto.response;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "USD",
    "AUD",
    "BGN",
    "CAD",
    "CHF",
    "CNY",
    "EGP",
    "EUR",
    "GBP"
})
@Generated("jsonschema2pojo")
public class ConversionRates {

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

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("USD")
    public Double getUsd() {
        return usd;
    }

    @JsonProperty("USD")
    public void setUsd(Double usd) {
        this.usd = usd;
    }

    @JsonProperty("AUD")
    public Double getAud() {
        return aud;
    }

    @JsonProperty("AUD")
    public void setAud(Double aud) {
        this.aud = aud;
    }

    @JsonProperty("BGN")
    public Double getBgn() {
        return bgn;
    }

    @JsonProperty("BGN")
    public void setBgn(Double bgn) {
        this.bgn = bgn;
    }

    @JsonProperty("GEL")
    public Double getGel() {
        return gel;
    }

    @JsonProperty("GEL")
    public void setGel(Double gel) {
        this.gel = gel;
    }

    @JsonProperty("CAD")
    public Double getCad() {
        return cad;
    }

    @JsonProperty("CAD")
    public void setCad(Double cad) {
        this.cad = cad;
    }

    @JsonProperty("CHF")
    public Double getChf() {
        return chf;
    }

    @JsonProperty("CHF")
    public void setChf(Double chf) {
        this.chf = chf;
    }

    @JsonProperty("CNY")
    public Double getCny() {
        return cny;
    }

    @JsonProperty("CNY")
    public void setCny(Double cny) {
        this.cny = cny;
    }

    @JsonProperty("EGP")
    public Double getEgp() {
        return egp;
    }

    @JsonProperty("EGP")
    public void setEgp(Double egp) {
        this.egp = egp;
    }

    @JsonProperty("EUR")
    public Double getEur() {
        return eur;
    }

    @JsonProperty("EUR")
    public void setEur(Double eur) {
        this.eur = eur;
    }

    @JsonProperty("GBP")
    public Double getGbp() {
        return gbp;
    }

    @JsonProperty("GBP")
    public void setGbp(Double gbp) {
        this.gbp = gbp;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
