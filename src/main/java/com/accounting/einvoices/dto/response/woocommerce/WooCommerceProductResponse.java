
package com.accounting.einvoices.dto.response.woocommerce;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.accounting.einvoices.dto.response.Category;
import com.accounting.einvoices.dto.response.Dimensions;
import com.accounting.einvoices.dto.response.Links;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "slug",
    "permalink",
    "date_created",
    "date_created_gmt",
    "date_modified",
    "date_modified_gmt",
    "type",
    "status",
    "featured",
    "catalog_visibility",
    "description",
    "short_description",
    "sku",
    "price",
    "regular_price",
    "sale_price",
    "date_on_sale_from",
    "date_on_sale_from_gmt",
    "date_on_sale_to",
    "date_on_sale_to_gmt",
    "on_sale",
    "purchasable",
    "total_sales",
    "virtual",
    "downloadable",
    "downloads",
    "download_limit",
    "download_expiry",
    "external_url",
    "button_text",
    "tax_status",
    "tax_class",
    "manage_stock",
    "stock_quantity",
    "backorders",
    "backorders_allowed",
    "backordered",
    "low_stock_amount",
    "sold_individually",
    "weight",
    "dimensions",
    "shipping_required",
    "shipping_taxable",
    "shipping_class",
    "shipping_class_id",
    "reviews_allowed",
    "average_rating",
    "rating_count",
    "upsell_ids",
    "cross_sell_ids",
    "parent_id",
    "purchase_note",
    "categories",
    "tags",
    "images",
    "attributes",
    "default_attributes",
    "variations",
    "grouped_products",
    "menu_order",
    "price_html",
    "related_ids",
    "meta_data",
    "stock_status",
    "has_options",
    "post_password",
    "global_unique_id",
    "_links"
})
@Generated("jsonschema2pojo")
public class WooCommerceProductResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_created_gmt")
    private String dateCreatedGmt;
    @JsonProperty("date_modified")
    private String dateModified;
    @JsonProperty("date_modified_gmt")
    private String dateModifiedGmt;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("featured")
    private Boolean featured;
    @JsonProperty("catalog_visibility")
    private String catalogVisibility;
    @JsonProperty("description")
    private String description;
    @JsonProperty("short_description")
    private String shortDescription;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("price")
    private String price;
    @JsonProperty("regular_price")
    private String regularPrice;
    @JsonProperty("sale_price")
    private String salePrice;
    @JsonProperty("date_on_sale_from")
    private Object dateOnSaleFrom;
    @JsonProperty("date_on_sale_from_gmt")
    private Object dateOnSaleFromGmt;
    @JsonProperty("date_on_sale_to")
    private Object dateOnSaleTo;
    @JsonProperty("date_on_sale_to_gmt")
    private Object dateOnSaleToGmt;
    @JsonProperty("on_sale")
    private Boolean onSale;
    @JsonProperty("purchasable")
    private Boolean purchasable;
    @JsonProperty("total_sales")
    private Integer totalSales;
    @JsonProperty("virtual")
    private Boolean virtual;
    @JsonProperty("downloadable")
    private Boolean downloadable;
    @JsonProperty("downloads")
    private List<Object> downloads;
    @JsonProperty("download_limit")
    private Integer downloadLimit;
    @JsonProperty("download_expiry")
    private Integer downloadExpiry;
    @JsonProperty("external_url")
    private String externalUrl;
    @JsonProperty("button_text")
    private String buttonText;
    @JsonProperty("tax_status")
    private String taxStatus;
    @JsonProperty("tax_class")
    private String taxClass;
    @JsonProperty("manage_stock")
    private Boolean manageStock;
    @JsonProperty("stock_quantity")
    private Object stockQuantity;
    @JsonProperty("backorders")
    private String backorders;
    @JsonProperty("backorders_allowed")
    private Boolean backordersAllowed;
    @JsonProperty("backordered")
    private Boolean backordered;
    @JsonProperty("low_stock_amount")
    private Object lowStockAmount;
    @JsonProperty("sold_individually")
    private Boolean soldIndividually;
    @JsonProperty("weight")
    private String weight;
    @JsonProperty("dimensions")
    private Dimensions dimensions;
    @JsonProperty("shipping_required")
    private Boolean shippingRequired;
    @JsonProperty("shipping_taxable")
    private Boolean shippingTaxable;
    @JsonProperty("shipping_class")
    private String shippingClass;
    @JsonProperty("shipping_class_id")
    private Integer shippingClassId;
    @JsonProperty("reviews_allowed")
    private Boolean reviewsAllowed;
    @JsonProperty("average_rating")
    private String averageRating;
    @JsonProperty("rating_count")
    private Integer ratingCount;
    @JsonProperty("upsell_ids")
    private List<Object> upsellIds;
    @JsonProperty("cross_sell_ids")
    private List<Object> crossSellIds;
    @JsonProperty("parent_id")
    private Integer parentId;
    @JsonProperty("purchase_note")
    private String purchaseNote;
    @JsonProperty("categories")
    private List<Category> categories;
    @JsonProperty("tags")
    private List<Object> tags;
    @JsonProperty("images")
    private List<Object> images;
    @JsonProperty("attributes")
    private List<Object> attributes;
    @JsonProperty("default_attributes")
    private List<Object> defaultAttributes;
    @JsonProperty("variations")
    private List<Object> variations;
    @JsonProperty("grouped_products")
    private List<Object> groupedProducts;
    @JsonProperty("menu_order")
    private Integer menuOrder;
    @JsonProperty("price_html")
    private String priceHtml;
    @JsonProperty("related_ids")
    private List<Object> relatedIds;
    @JsonProperty("meta_data")
    private List<Object> metaData;
    @JsonProperty("stock_status")
    private String stockStatus;
    @JsonProperty("has_options")
    private Boolean hasOptions;
    @JsonProperty("post_password")
    private String postPassword;
    @JsonProperty("global_unique_id")
    private String globalUniqueId;
    @JsonProperty("_links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("permalink")
    public String getPermalink() {
        return permalink;
    }

    @JsonProperty("permalink")
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    @JsonProperty("date_created")
    public String getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("date_created")
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonProperty("date_created_gmt")
    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    @JsonProperty("date_created_gmt")
    public void setDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
    }

    @JsonProperty("date_modified")
    public String getDateModified() {
        return dateModified;
    }

    @JsonProperty("date_modified")
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    @JsonProperty("date_modified_gmt")
    public String getDateModifiedGmt() {
        return dateModifiedGmt;
    }

    @JsonProperty("date_modified_gmt")
    public void setDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("featured")
    public Boolean getFeatured() {
        return featured;
    }

    @JsonProperty("featured")
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    @JsonProperty("catalog_visibility")
    public String getCatalogVisibility() {
        return catalogVisibility;
    }

    @JsonProperty("catalog_visibility")
    public void setCatalogVisibility(String catalogVisibility) {
        this.catalogVisibility = catalogVisibility;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("short_description")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("short_description")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("regular_price")
    public String getRegularPrice() {
        return regularPrice;
    }

    @JsonProperty("regular_price")
    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    @JsonProperty("sale_price")
    public String getSalePrice() {
        return salePrice;
    }

    @JsonProperty("sale_price")
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    @JsonProperty("date_on_sale_from")
    public Object getDateOnSaleFrom() {
        return dateOnSaleFrom;
    }

    @JsonProperty("date_on_sale_from")
    public void setDateOnSaleFrom(Object dateOnSaleFrom) {
        this.dateOnSaleFrom = dateOnSaleFrom;
    }

    @JsonProperty("date_on_sale_from_gmt")
    public Object getDateOnSaleFromGmt() {
        return dateOnSaleFromGmt;
    }

    @JsonProperty("date_on_sale_from_gmt")
    public void setDateOnSaleFromGmt(Object dateOnSaleFromGmt) {
        this.dateOnSaleFromGmt = dateOnSaleFromGmt;
    }

    @JsonProperty("date_on_sale_to")
    public Object getDateOnSaleTo() {
        return dateOnSaleTo;
    }

    @JsonProperty("date_on_sale_to")
    public void setDateOnSaleTo(Object dateOnSaleTo) {
        this.dateOnSaleTo = dateOnSaleTo;
    }

    @JsonProperty("date_on_sale_to_gmt")
    public Object getDateOnSaleToGmt() {
        return dateOnSaleToGmt;
    }

    @JsonProperty("date_on_sale_to_gmt")
    public void setDateOnSaleToGmt(Object dateOnSaleToGmt) {
        this.dateOnSaleToGmt = dateOnSaleToGmt;
    }

    @JsonProperty("on_sale")
    public Boolean getOnSale() {
        return onSale;
    }

    @JsonProperty("on_sale")
    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    @JsonProperty("purchasable")
    public Boolean getPurchasable() {
        return purchasable;
    }

    @JsonProperty("purchasable")
    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    @JsonProperty("total_sales")
    public Integer getTotalSales() {
        return totalSales;
    }

    @JsonProperty("total_sales")
    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    @JsonProperty("virtual")
    public Boolean getVirtual() {
        return virtual;
    }

    @JsonProperty("virtual")
    public void setVirtual(Boolean virtual) {
        this.virtual = virtual;
    }

    @JsonProperty("downloadable")
    public Boolean getDownloadable() {
        return downloadable;
    }

    @JsonProperty("downloadable")
    public void setDownloadable(Boolean downloadable) {
        this.downloadable = downloadable;
    }

    @JsonProperty("downloads")
    public List<Object> getDownloads() {
        return downloads;
    }

    @JsonProperty("downloads")
    public void setDownloads(List<Object> downloads) {
        this.downloads = downloads;
    }

    @JsonProperty("download_limit")
    public Integer getDownloadLimit() {
        return downloadLimit;
    }

    @JsonProperty("download_limit")
    public void setDownloadLimit(Integer downloadLimit) {
        this.downloadLimit = downloadLimit;
    }

    @JsonProperty("download_expiry")
    public Integer getDownloadExpiry() {
        return downloadExpiry;
    }

    @JsonProperty("download_expiry")
    public void setDownloadExpiry(Integer downloadExpiry) {
        this.downloadExpiry = downloadExpiry;
    }

    @JsonProperty("external_url")
    public String getExternalUrl() {
        return externalUrl;
    }

    @JsonProperty("external_url")
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    @JsonProperty("button_text")
    public String getButtonText() {
        return buttonText;
    }

    @JsonProperty("button_text")
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    @JsonProperty("tax_status")
    public String getTaxStatus() {
        return taxStatus;
    }

    @JsonProperty("tax_status")
    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    @JsonProperty("tax_class")
    public String getTaxClass() {
        return taxClass;
    }

    @JsonProperty("tax_class")
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    @JsonProperty("manage_stock")
    public Boolean getManageStock() {
        return manageStock;
    }

    @JsonProperty("manage_stock")
    public void setManageStock(Boolean manageStock) {
        this.manageStock = manageStock;
    }

    @JsonProperty("stock_quantity")
    public Object getStockQuantity() {
        return stockQuantity;
    }

    @JsonProperty("stock_quantity")
    public void setStockQuantity(Object stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @JsonProperty("backorders")
    public String getBackorders() {
        return backorders;
    }

    @JsonProperty("backorders")
    public void setBackorders(String backorders) {
        this.backorders = backorders;
    }

    @JsonProperty("backorders_allowed")
    public Boolean getBackordersAllowed() {
        return backordersAllowed;
    }

    @JsonProperty("backorders_allowed")
    public void setBackordersAllowed(Boolean backordersAllowed) {
        this.backordersAllowed = backordersAllowed;
    }

    @JsonProperty("backordered")
    public Boolean getBackordered() {
        return backordered;
    }

    @JsonProperty("backordered")
    public void setBackordered(Boolean backordered) {
        this.backordered = backordered;
    }

    @JsonProperty("low_stock_amount")
    public Object getLowStockAmount() {
        return lowStockAmount;
    }

    @JsonProperty("low_stock_amount")
    public void setLowStockAmount(Object lowStockAmount) {
        this.lowStockAmount = lowStockAmount;
    }

    @JsonProperty("sold_individually")
    public Boolean getSoldIndividually() {
        return soldIndividually;
    }

    @JsonProperty("sold_individually")
    public void setSoldIndividually(Boolean soldIndividually) {
        this.soldIndividually = soldIndividually;
    }

    @JsonProperty("weight")
    public String getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(String weight) {
        this.weight = weight;
    }

    @JsonProperty("dimensions")
    public Dimensions getDimensions() {
        return dimensions;
    }

    @JsonProperty("dimensions")
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    @JsonProperty("shipping_required")
    public Boolean getShippingRequired() {
        return shippingRequired;
    }

    @JsonProperty("shipping_required")
    public void setShippingRequired(Boolean shippingRequired) {
        this.shippingRequired = shippingRequired;
    }

    @JsonProperty("shipping_taxable")
    public Boolean getShippingTaxable() {
        return shippingTaxable;
    }

    @JsonProperty("shipping_taxable")
    public void setShippingTaxable(Boolean shippingTaxable) {
        this.shippingTaxable = shippingTaxable;
    }

    @JsonProperty("shipping_class")
    public String getShippingClass() {
        return shippingClass;
    }

    @JsonProperty("shipping_class")
    public void setShippingClass(String shippingClass) {
        this.shippingClass = shippingClass;
    }

    @JsonProperty("shipping_class_id")
    public Integer getShippingClassId() {
        return shippingClassId;
    }

    @JsonProperty("shipping_class_id")
    public void setShippingClassId(Integer shippingClassId) {
        this.shippingClassId = shippingClassId;
    }

    @JsonProperty("reviews_allowed")
    public Boolean getReviewsAllowed() {
        return reviewsAllowed;
    }

    @JsonProperty("reviews_allowed")
    public void setReviewsAllowed(Boolean reviewsAllowed) {
        this.reviewsAllowed = reviewsAllowed;
    }

    @JsonProperty("average_rating")
    public String getAverageRating() {
        return averageRating;
    }

    @JsonProperty("average_rating")
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    @JsonProperty("rating_count")
    public Integer getRatingCount() {
        return ratingCount;
    }

    @JsonProperty("rating_count")
    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    @JsonProperty("upsell_ids")
    public List<Object> getUpsellIds() {
        return upsellIds;
    }

    @JsonProperty("upsell_ids")
    public void setUpsellIds(List<Object> upsellIds) {
        this.upsellIds = upsellIds;
    }

    @JsonProperty("cross_sell_ids")
    public List<Object> getCrossSellIds() {
        return crossSellIds;
    }

    @JsonProperty("cross_sell_ids")
    public void setCrossSellIds(List<Object> crossSellIds) {
        this.crossSellIds = crossSellIds;
    }

    @JsonProperty("parent_id")
    public Integer getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("purchase_note")
    public String getPurchaseNote() {
        return purchaseNote;
    }

    @JsonProperty("purchase_note")
    public void setPurchaseNote(String purchaseNote) {
        this.purchaseNote = purchaseNote;
    }

    @JsonProperty("categories")
    public List<Category> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("images")
    public List<Object> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<Object> images) {
        this.images = images;
    }

    @JsonProperty("attributes")
    public List<Object> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("default_attributes")
    public List<Object> getDefaultAttributes() {
        return defaultAttributes;
    }

    @JsonProperty("default_attributes")
    public void setDefaultAttributes(List<Object> defaultAttributes) {
        this.defaultAttributes = defaultAttributes;
    }

    @JsonProperty("variations")
    public List<Object> getVariations() {
        return variations;
    }

    @JsonProperty("variations")
    public void setVariations(List<Object> variations) {
        this.variations = variations;
    }

    @JsonProperty("grouped_products")
    public List<Object> getGroupedProducts() {
        return groupedProducts;
    }

    @JsonProperty("grouped_products")
    public void setGroupedProducts(List<Object> groupedProducts) {
        this.groupedProducts = groupedProducts;
    }

    @JsonProperty("menu_order")
    public Integer getMenuOrder() {
        return menuOrder;
    }

    @JsonProperty("menu_order")
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    @JsonProperty("price_html")
    public String getPriceHtml() {
        return priceHtml;
    }

    @JsonProperty("price_html")
    public void setPriceHtml(String priceHtml) {
        this.priceHtml = priceHtml;
    }

    @JsonProperty("related_ids")
    public List<Object> getRelatedIds() {
        return relatedIds;
    }

    @JsonProperty("related_ids")
    public void setRelatedIds(List<Object> relatedIds) {
        this.relatedIds = relatedIds;
    }

    @JsonProperty("meta_data")
    public List<Object> getMetaData() {
        return metaData;
    }

    @JsonProperty("meta_data")
    public void setMetaData(List<Object> metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("stock_status")
    public String getStockStatus() {
        return stockStatus;
    }

    @JsonProperty("stock_status")
    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    @JsonProperty("has_options")
    public Boolean getHasOptions() {
        return hasOptions;
    }

    @JsonProperty("has_options")
    public void setHasOptions(Boolean hasOptions) {
        this.hasOptions = hasOptions;
    }

    @JsonProperty("post_password")
    public String getPostPassword() {
        return postPassword;
    }

    @JsonProperty("post_password")
    public void setPostPassword(String postPassword) {
        this.postPassword = postPassword;
    }

    @JsonProperty("global_unique_id")
    public String getGlobalUniqueId() {
        return globalUniqueId;
    }

    @JsonProperty("global_unique_id")
    public void setGlobalUniqueId(String globalUniqueId) {
        this.globalUniqueId = globalUniqueId;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
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
