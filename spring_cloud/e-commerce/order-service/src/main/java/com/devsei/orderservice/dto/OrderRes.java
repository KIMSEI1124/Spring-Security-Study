package com.devsei.orderservice.dto;

import com.devsei.orderservice.domain.OrderJapEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRes {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createAt;
    private String orderId;

    public static OrderRes from(OrderJapEntity order) {
        return OrderRes.builder()
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .createAt(order.getCreateAt())
                .orderId(order.getOrderId())
                .build();
    }
}
