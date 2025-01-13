
package com.accounting.einvoices.dto.response.paypal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.accounting.einvoices.dto.request.paypal.subscription.ShippingAmount;
import com.accounting.einvoices.dto.request.paypal.subscription.Subscriber;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "plan_id",
    "start_time",
    "quantity",
    "shipping_amount",
    "subscriber",
    "billing_info",
    "create_time",
    "update_time",
    "links",
    "status",
    "status_update_time"
})
@Generated("jsonschema2pojo")
public class SubscriptionDetailsResponse {

    @JsonProperty("id")
    private String id;
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
    @JsonProperty("billing_info")
    private BillingInfo billingInfo;
    @JsonProperty("create_time")
    private String createTime;
    @JsonProperty("update_time")
    private String updateTime;
    @JsonProperty("links")
    private List<Link> links;
    @JsonProperty("status")
    private String status;
    @JsonProperty("status_update_time")
    private String statusUpdateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

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

    @JsonProperty("billing_info")
    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    @JsonProperty("billing_info")
    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    @JsonProperty("create_time")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("create_time")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("update_time")
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("status_update_time")
    public String getStatusUpdateTime() {
        return statusUpdateTime;
    }

    @JsonProperty("status_update_time")
    public void setStatusUpdateTime(String statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
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
