
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
    "payer_selected",
    "payee_preferred"
})
@Generated("jsonschema2pojo")
public class PaymentMethod {

    @JsonProperty("payer_selected")
    private String payerSelected;
    @JsonProperty("payee_preferred")
    private String payeePreferred;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("payer_selected")
    public String getPayerSelected() {
        return payerSelected;
    }

    @JsonProperty("payer_selected")
    public void setPayerSelected(String payerSelected) {
        this.payerSelected = payerSelected;
    }

    @JsonProperty("payee_preferred")
    public String getPayeePreferred() {
        return payeePreferred;
    }

    @JsonProperty("payee_preferred")
    public void setPayeePreferred(String payeePreferred) {
        this.payeePreferred = payeePreferred;
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
