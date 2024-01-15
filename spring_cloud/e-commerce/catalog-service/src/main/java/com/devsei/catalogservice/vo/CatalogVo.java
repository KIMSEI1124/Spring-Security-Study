package com.devsei.catalogservice.vo;

import lombok.Data;

@Data
public class CatalogVo {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;
}
