package com.devsei.catalogservice.dto;

import com.devsei.catalogservice.domain.CatalogJpaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogRes {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private Date createAt;

    public static CatalogRes from(CatalogJpaEntity catalog) {
        return CatalogRes.builder()
                .productId(catalog.getProductId())
                .productName(catalog.getProductName())
                .unitPrice(catalog.getUnitPrice())
                .stock(catalog.getStock())
                .build();
    }
}
