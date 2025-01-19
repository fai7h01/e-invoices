package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WooCommerceCredentialsDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    @Pattern(regexp = "^(https?://)?(www\\\\.)?([a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}(/\\\\S*)?$")
    @JsonProperty("base_url")
    private String baseUrl;
    @NotBlank
    @Pattern(regexp = "^ck_[a-f0-9]{40}$")
    @JsonProperty("consumer_key")
    private String consumerKey;
    @NotBlank
    @Pattern(regexp = "^cs_[a-f0-9]{40}$")
    @JsonProperty("consumer_secret")
    private String consumerSecret;
    private CompanyDTO companyDTO;
}
