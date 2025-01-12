
package com.accounting.einvoices.dto.response.paypal;

import java.util.LinkedHashMap;
import java.util.List;
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
    "outstanding_balance",
    "cycle_executions",
    "last_payment",
    "next_billing_time",
    "failed_payments_count"
})
@Generated("jsonschema2pojo")
public class BillingInfo {

    @JsonProperty("outstanding_balance")
    private OutstandingBalance outstandingBalance;
    @JsonProperty("cycle_executions")
    private List<CycleExecution> cycleExecutions;
    @JsonProperty("last_payment")
    private LastPayment lastPayment;
    @JsonProperty("next_billing_time")
    private String nextBillingTime;
    @JsonProperty("failed_payments_count")
    private Integer failedPaymentsCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("outstanding_balance")
    public OutstandingBalance getOutstandingBalance() {
        return outstandingBalance;
    }

    @JsonProperty("outstanding_balance")
    public void setOutstandingBalance(OutstandingBalance outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    @JsonProperty("cycle_executions")
    public List<CycleExecution> getCycleExecutions() {
        return cycleExecutions;
    }

    @JsonProperty("cycle_executions")
    public void setCycleExecutions(List<CycleExecution> cycleExecutions) {
        this.cycleExecutions = cycleExecutions;
    }

    @JsonProperty("last_payment")
    public LastPayment getLastPayment() {
        return lastPayment;
    }

    @JsonProperty("last_payment")
    public void setLastPayment(LastPayment lastPayment) {
        this.lastPayment = lastPayment;
    }

    @JsonProperty("next_billing_time")
    public String getNextBillingTime() {
        return nextBillingTime;
    }

    @JsonProperty("next_billing_time")
    public void setNextBillingTime(String nextBillingTime) {
        this.nextBillingTime = nextBillingTime;
    }

    @JsonProperty("failed_payments_count")
    public Integer getFailedPaymentsCount() {
        return failedPaymentsCount;
    }

    @JsonProperty("failed_payments_count")
    public void setFailedPaymentsCount(Integer failedPaymentsCount) {
        this.failedPaymentsCount = failedPaymentsCount;
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
