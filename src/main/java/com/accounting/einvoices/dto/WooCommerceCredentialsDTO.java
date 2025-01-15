package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WooCommerceCredentialsDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty("base_url")
    private String baseUrl;
    @JsonProperty("consumer_key")
    private String consumerKey;
    @JsonProperty("consumer_secret")
    private String consumerSecret;

    private UserDTO user;
}
