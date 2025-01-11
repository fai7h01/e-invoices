
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
    "percentage",
    "inclusive"
})
@Generated("jsonschema2pojo")
public class Taxes {

    @JsonProperty("percentage")
    private String percentage;
    @JsonProperty("inclusive")
    private Boolean inclusive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("percentage")
    public String getPercentage() {
        return percentage;
    }

    @JsonProperty("percentage")
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    @JsonProperty("inclusive")
    public Boolean getInclusive() {
        return inclusive;
    }

    @JsonProperty("inclusive")
    public void setInclusive(Boolean inclusive) {
        this.inclusive = inclusive;
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
