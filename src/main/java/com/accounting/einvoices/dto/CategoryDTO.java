package com.accounting.einvoices.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    private String description;

    private CompanyDTO company;

    private boolean hasProduct;
}
