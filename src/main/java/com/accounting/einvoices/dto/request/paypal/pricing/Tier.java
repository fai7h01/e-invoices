
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
    "starting_quantity",
    "ending_quantity",
    "amount"
})
@Generated("jsonschema2pojo")
public class Tier {

    @JsonProperty("starting_quantity")
    private String startingQuantity;
    @JsonProperty("ending_quantity")
    private String endingQuantity;
    @JsonProperty("amount")
    private Amount amount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("starting_quantity")
    public String getStartingQuantity() {
        return startingQuantity;
    }

    @JsonProperty("starting_quantity")
    public void setStartingQuantity(String startingQuantity) {
        this.startingQuantity = startingQuantity;
    }

    @JsonProperty("ending_quantity")
    public String getEndingQuantity() {
        return endingQuantity;
    }

    @JsonProperty("ending_quantity")
    public void setEndingQuantity(String endingQuantity) {
        this.endingQuantity = endingQuantity;
    }

    @JsonProperty("amount")
    public Amount getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
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
