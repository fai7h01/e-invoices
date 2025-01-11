
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
    "interval_unit",
    "interval_count"
})
@Generated("jsonschema2pojo")
public class Frequency {

    @JsonProperty("interval_unit")
    private String intervalUnit;
    @JsonProperty("interval_count")
    private Integer intervalCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("interval_unit")
    public String getIntervalUnit() {
        return intervalUnit;
    }

    @JsonProperty("interval_unit")
    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    @JsonProperty("interval_count")
    public Integer getIntervalCount() {
        return intervalCount;
    }

    @JsonProperty("interval_count")
    public void setIntervalCount(Integer intervalCount) {
        this.intervalCount = intervalCount;
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
