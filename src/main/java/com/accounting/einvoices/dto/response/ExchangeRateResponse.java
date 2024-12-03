
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "result",
    "documentation",
    "terms_of_use",
    "time_last_update_unix",
    "time_last_update_utc",
    "time_next_update_unix",
    "time_next_update_utc",
    "base_code",
    "target_code",
    "conversion_rate"
})
@Generated("jsonschema2pojo")
public class ExchangeRateResponse {

    @JsonProperty("result")
    private String result;
    @JsonProperty("documentation")
    private String documentation;
    @JsonProperty("terms_of_use")
    private String termsOfUse;
    @JsonProperty("time_last_update_unix")
    private Integer timeLastUpdateUnix;
    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;
    @JsonProperty("time_next_update_unix")
    private Integer timeNextUpdateUnix;
    @JsonProperty("time_next_update_utc")
    private String timeNextUpdateUtc;
    @JsonProperty("base_code")
    private String baseCode;
    @JsonProperty("target_code")
    private String targetCode;
    @JsonProperty("conversion_rate")
    private Double conversionRate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("documentation")
    public String getDocumentation() {
        return documentation;
    }

    @JsonProperty("documentation")
    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    @JsonProperty("terms_of_use")
    public String getTermsOfUse() {
        return termsOfUse;
    }

    @JsonProperty("terms_of_use")
    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    @JsonProperty("time_last_update_unix")
    public Integer getTimeLastUpdateUnix() {
        return timeLastUpdateUnix;
    }

    @JsonProperty("time_last_update_unix")
    public void setTimeLastUpdateUnix(Integer timeLastUpdateUnix) {
        this.timeLastUpdateUnix = timeLastUpdateUnix;
    }

    @JsonProperty("time_last_update_utc")
    public String getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    @JsonProperty("time_last_update_utc")
    public void setTimeLastUpdateUtc(String timeLastUpdateUtc) {
        this.timeLastUpdateUtc = timeLastUpdateUtc;
    }

    @JsonProperty("time_next_update_unix")
    public Integer getTimeNextUpdateUnix() {
        return timeNextUpdateUnix;
    }

    @JsonProperty("time_next_update_unix")
    public void setTimeNextUpdateUnix(Integer timeNextUpdateUnix) {
        this.timeNextUpdateUnix = timeNextUpdateUnix;
    }

    @JsonProperty("time_next_update_utc")
    public String getTimeNextUpdateUtc() {
        return timeNextUpdateUtc;
    }

    @JsonProperty("time_next_update_utc")
    public void setTimeNextUpdateUtc(String timeNextUpdateUtc) {
        this.timeNextUpdateUtc = timeNextUpdateUtc;
    }

    @JsonProperty("base_code")
    public String getBaseCode() {
        return baseCode;
    }

    @JsonProperty("base_code")
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    @JsonProperty("target_code")
    public String getTargetCode() {
        return targetCode;
    }

    @JsonProperty("target_code")
    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    @JsonProperty("conversion_rate")
    public Double getConversionRate() {
        return conversionRate;
    }

    @JsonProperty("conversion_rate")
    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
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
