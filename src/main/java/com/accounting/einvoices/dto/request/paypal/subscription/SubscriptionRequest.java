
package com.accounting.einvoices.dto.request.paypal.subscription;

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
    "plan_id",
    "start_time",
    "quantity",
    "shipping_amount",
    "subscriber",
    "application_context"
})
@Generated("jsonschema2pojo")
public class SubscriptionRequest {

    @JsonProperty("plan_id")
    private String planId;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("shipping_amount")
    private ShippingAmount shippingAmount;
    @JsonProperty("subscriber")
    private Subscriber subscriber;
    @JsonProperty("application_context")
    private ApplicationContext applicationContext;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("plan_id")
    public String getPlanId() {
        return planId;
    }

    @JsonProperty("plan_id")
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @JsonProperty("start_time")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("shipping_amount")
    public ShippingAmount getShippingAmount() {
        return shippingAmount;
    }

    @JsonProperty("shipping_amount")
    public void setShippingAmount(ShippingAmount shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    @JsonProperty("subscriber")
    public Subscriber getSubscriber() {
        return subscriber;
    }

    @JsonProperty("subscriber")
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @JsonProperty("application_context")
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @JsonProperty("application_context")
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
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
