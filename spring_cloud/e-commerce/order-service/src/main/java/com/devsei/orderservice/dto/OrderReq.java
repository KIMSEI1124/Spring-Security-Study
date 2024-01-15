package com.devsei.orderservice.dto;

import lombok.Data;

@Data
public class OrderReq {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
}
