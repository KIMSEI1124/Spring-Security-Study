package com.devsei.orderservice.vo;

import com.devsei.orderservice.domain.OrderJapEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Builder
@Getter
public class OrderVo {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;

    public static OrderVo from(OrderJapEntity entity) {
        return OrderVo.builder()
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .totalPrice(entity.getTotalPrice())
                .orderId(entity.getOrderId())
                .userId(entity.getUserId())
                .build();
    }
}
