package com.accounting.einvoices.dto;

import com.accounting.einvoices.enums.CategoryIcon;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private CompanyDTO company;

    private CategoryIcon icon;

    @JsonIgnore
    private boolean hasProduct;
}
