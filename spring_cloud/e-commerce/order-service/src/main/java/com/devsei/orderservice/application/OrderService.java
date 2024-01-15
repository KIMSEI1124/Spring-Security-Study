package com.devsei.orderservice.application;

import com.devsei.orderservice.domain.OrderJapEntity;
import com.devsei.orderservice.dto.OrderReq;
import com.devsei.orderservice.dto.OrderRes;

public interface OrderService {
    OrderRes createOrder(OrderReq req, String userId);

    Iterable<OrderJapEntity> findAllByUserId(String userId);

    OrderRes getOrderByOrderId(String orderId);
}
