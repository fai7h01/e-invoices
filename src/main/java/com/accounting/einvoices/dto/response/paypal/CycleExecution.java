
package com.accounting.einvoices.dto.response.paypal;

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
    "tenure_type",
    "sequence",
    "cycles_completed",
    "cycles_remaining",
    "total_cycles"
})
@Generated("jsonschema2pojo")
public class CycleExecution {

    @JsonProperty("tenure_type")
    private String tenureType;
    @JsonProperty("sequence")
    private Integer sequence;
    @JsonProperty("cycles_completed")
    private Integer cyclesCompleted;
    @JsonProperty("cycles_remaining")
    private Integer cyclesRemaining;
    @JsonProperty("total_cycles")
    private Integer totalCycles;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

    @JsonProperty("cycles_completed")
    public Integer getCyclesCompleted() {
        return cyclesCompleted;
    }

    @JsonProperty("cycles_completed")
    public void setCyclesCompleted(Integer cyclesCompleted) {
        this.cyclesCompleted = cyclesCompleted;
    }

    @JsonProperty("cycles_remaining")
    public Integer getCyclesRemaining() {
        return cyclesRemaining;
    }

    @JsonProperty("cycles_remaining")
    public void setCyclesRemaining(Integer cyclesRemaining) {
        this.cyclesRemaining = cyclesRemaining;
    }

    @JsonProperty("total_cycles")
    public Integer getTotalCycles() {
        return totalCycles;
    }

    @JsonProperty("total_cycles")
    public void setTotalCycles(Integer totalCycles) {
        this.totalCycles = totalCycles;
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
