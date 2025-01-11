
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
    "total_items",
    "total_pages",
    "products",
    "links"
})
@Generated("jsonschema2pojo")
public class CatalogProductListResponse {

    @JsonProperty("total_items")
    private Integer totalItems;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("products")
    private List<CatalogProductResponse> products;
    @JsonProperty("links")
    private List<Link> links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("total_items")
    public Integer getTotalItems() {
        return totalItems;
    }

    @JsonProperty("total_items")
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    @JsonProperty("total_pages")
    public Integer getTotalPages() {
        return totalPages;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty("products")
    public List<CatalogProductResponse> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<CatalogProductResponse> products) {
        this.products = products;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
        this.links = links;
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
