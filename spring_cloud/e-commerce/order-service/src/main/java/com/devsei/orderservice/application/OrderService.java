package com.devsei.orderservice.application;

import com.devsei.orderservice.domain.OrderJapEntity;
import com.devsei.orderservice.dto.OrderRes;
import com.devsei.orderservice.vo.OrderVo;

public interface OrderService {
    OrderRes createOrder(OrderVo vo);

    Iterable<OrderJapEntity> findAllByUserId(String userId);

    OrderRes getOrderByOrderId(String orderId);
}
