
package com.accounting.einvoices.dto.request.paypal;

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
    "product_id",
    "name",
    "description",
    "status",
    "billing_cycles",
    "payment_preferences",
    "taxes"
})
@Generated("jsonschema2pojo")
public class PlanRequest {

    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("billing_cycles")
    private List<BillingCycle> billingCycles;
    @JsonProperty("payment_preferences")
    private PaymentPreferences paymentPreferences;
    @JsonProperty("taxes")
    private Taxes taxes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("product_id")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("billing_cycles")
    public List<BillingCycle> getBillingCycles() {
        return billingCycles;
    }

    @JsonProperty("billing_cycles")
    public void setBillingCycles(List<BillingCycle> billingCycles) {
        this.billingCycles = billingCycles;
    }

    @JsonProperty("payment_preferences")
    public PaymentPreferences getPaymentPreferences() {
        return paymentPreferences;
    }

    @JsonProperty("payment_preferences")
    public void setPaymentPreferences(PaymentPreferences paymentPreferences) {
        this.paymentPreferences = paymentPreferences;
    }

    @JsonProperty("taxes")
    public Taxes getTaxes() {
        return taxes;
    }

    @JsonProperty("taxes")
    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
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
