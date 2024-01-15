package com.devsei.orderservice.vo;

import lombok.Data;

@Data
public class OrderVo {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;
}
