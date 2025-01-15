
package com.accounting.einvoices.dto.response.woocommerce;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.accounting.einvoices.dto.response.TargetHints;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "targetHints"
})
@Generated("jsonschema2pojo")
public class Self {

    @JsonProperty("href")
    private String href;
    @JsonProperty("targetHints")
    private TargetHints targetHints;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("targetHints")
    public TargetHints getTargetHints() {
        return targetHints;
    }

    @JsonProperty("targetHints")
    public void setTargetHints(TargetHints targetHints) {
        this.targetHints = targetHints;
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
