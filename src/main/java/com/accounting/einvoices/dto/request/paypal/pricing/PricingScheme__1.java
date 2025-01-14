
package com.accounting.einvoices.dto.request.paypal.pricing;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.accounting.einvoices.dto.request.paypal.plan.FixedPrice;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fixed_price",
    "pricing_model",
    "tiers"
})
@Generated("jsonschema2pojo")
public class PricingScheme__1 {

    @JsonProperty("fixed_price")
    private FixedPrice fixedPrice;
    @JsonProperty("pricing_model")
    private String pricingModel;
    @JsonProperty("tiers")
    private List<Tier> tiers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("fixed_price")
    public FixedPrice getFixedPrice() {
        return fixedPrice;
    }

    @JsonProperty("fixed_price")
    public void setFixedPrice(FixedPrice fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    @JsonProperty("pricing_model")
    public String getPricingModel() {
        return pricingModel;
    }

    @JsonProperty("pricing_model")
    public void setPricingModel(String pricingModel) {
        this.pricingModel = pricingModel;
    }

    @JsonProperty("tiers")
    public List<Tier> getTiers() {
        return tiers;
    }

    @JsonProperty("tiers")
    public void setTiers(List<Tier> tiers) {
        this.tiers = tiers;
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
