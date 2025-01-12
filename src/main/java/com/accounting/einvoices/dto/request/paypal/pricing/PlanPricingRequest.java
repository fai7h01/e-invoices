
package com.accounting.einvoices.dto.request.paypal.pricing;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.accounting.einvoices.dto.request.paypal.PricingScheme;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pricing_schemes"
})
@Generated("jsonschema2pojo")
public class PlanPricingRequest {

    @JsonProperty("pricing_schemes")
    private List<PricingScheme> pricingSchemes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("pricing_schemes")
    public List<PricingScheme> getPricingSchemes() {
        return pricingSchemes;
    }

    @JsonProperty("pricing_schemes")
    public void setPricingSchemes(List<PricingScheme> pricingSchemes) {
        this.pricingSchemes = pricingSchemes;
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
