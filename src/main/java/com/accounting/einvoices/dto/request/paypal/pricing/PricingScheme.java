
package com.accounting.einvoices.dto.request.paypal.pricing;

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
    "billing_cycle_sequence",
    "pricing_scheme"
})
@Generated("jsonschema2pojo")
public class PricingScheme {

    @JsonProperty("billing_cycle_sequence")
    private Integer billingCycleSequence;
    @JsonProperty("pricing_scheme")
    private PricingScheme__1 pricingScheme;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("billing_cycle_sequence")
    public Integer getBillingCycleSequence() {
        return billingCycleSequence;
    }

    @JsonProperty("billing_cycle_sequence")
    public void setBillingCycleSequence(Integer billingCycleSequence) {
        this.billingCycleSequence = billingCycleSequence;
    }

    @JsonProperty("pricing_scheme")
    public PricingScheme__1 getPricingScheme() {
        return pricingScheme;
    }

    @JsonProperty("pricing_scheme")
    public void setPricingScheme(PricingScheme__1 pricingScheme) {
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
