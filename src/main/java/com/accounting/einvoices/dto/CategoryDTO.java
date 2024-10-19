package com.accounting.einvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompanyDTO company;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean hasProduct;
}
