
package com.accounting.einvoices.dto.request.paypal;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "frequency",
    "tenure_type",
    "sequence",
    "total_cycles",
    "pricing_scheme"
})
@Generated("jsonschema2pojo")
public class BillingCycle {

    @JsonProperty("frequency")
    private Frequency frequency;
    @JsonProperty("tenure_type")
    private String tenureType;
    @JsonProperty("sequence")
    private Integer sequence;
    @JsonProperty("total_cycles")
    private Integer totalCycles;
    @JsonProperty("pricing_scheme")
    private PricingScheme pricingScheme;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("frequency")
    public Frequency getFrequency() {
        return frequency;
    }

    @JsonProperty("frequency")
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @JsonProperty("tenure_type")
    public String getTenureType() {
        return tenureType;
    }

    @JsonProperty("tenure_type")
    public void setTenureType(String tenureType) {
        this.tenureType = tenureType;
    }

    @JsonProperty("sequence")
    public Integer getSequence() {
        return sequence;
    }

    @JsonProperty("sequence")
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @JsonProperty("total_cycles")
    public Integer getTotalCycles() {
        return totalCycles;
    }

    @JsonProperty("total_cycles")
    public void setTotalCycles(Integer totalCycles) {
        this.totalCycles = totalCycles;
    }

    @JsonProperty("pricing_scheme")
    public PricingScheme getPricingScheme() {
        return pricingScheme;
    }

    @JsonProperty("pricing_scheme")
    public void setPricingScheme(PricingScheme pricingScheme) {
        this.pricingScheme = pricingScheme;
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
