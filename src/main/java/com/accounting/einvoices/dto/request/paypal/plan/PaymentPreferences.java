
package com.accounting.einvoices.dto.request.paypal.plan;

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
    "auto_bill_outstanding",
    "setup_fee",
    "setup_fee_failure_action",
    "payment_failure_threshold"
})
@Generated("jsonschema2pojo")
public class PaymentPreferences {

    @JsonProperty("auto_bill_outstanding")
    private Boolean autoBillOutstanding;
    @JsonProperty("setup_fee")
    private SetupFee setupFee;
    @JsonProperty("setup_fee_failure_action")
    private String setupFeeFailureAction;
    @JsonProperty("payment_failure_threshold")
    private Integer paymentFailureThreshold;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("auto_bill_outstanding")
    public Boolean getAutoBillOutstanding() {
        return autoBillOutstanding;
    }

    @JsonProperty("auto_bill_outstanding")
    public void setAutoBillOutstanding(Boolean autoBillOutstanding) {
        this.autoBillOutstanding = autoBillOutstanding;
    }

    @JsonProperty("setup_fee")
    public SetupFee getSetupFee() {
        return setupFee;
    }

    @JsonProperty("setup_fee")
    public void setSetupFee(SetupFee setupFee) {
        this.setupFee = setupFee;
    }

    @JsonProperty("setup_fee_failure_action")
    public String getSetupFeeFailureAction() {
        return setupFeeFailureAction;
    }

    @JsonProperty("setup_fee_failure_action")
    public void setSetupFeeFailureAction(String setupFeeFailureAction) {
        this.setupFeeFailureAction = setupFeeFailureAction;
    }

    @JsonProperty("payment_failure_threshold")
    public Integer getPaymentFailureThreshold() {
        return paymentFailureThreshold;
    }

    @JsonProperty("payment_failure_threshold")
    public void setPaymentFailureThreshold(Integer paymentFailureThreshold) {
        this.paymentFailureThreshold = paymentFailureThreshold;
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
