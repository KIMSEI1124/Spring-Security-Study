package com.devsei.orderservice.application;

import com.devsei.orderservice.domain.OrderJapEntity;
import com.devsei.orderservice.domain.OrderRepository;
import com.devsei.orderservice.dto.OrderRes;
import com.devsei.orderservice.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderRes createOrder(OrderVo vo) {
        OrderJapEntity orderJapEntity = OrderJapEntity.builder()
                .productId(vo.getProductId())
                .quantity(vo.getQuantity())
                .userId(vo.getUserId())
                .unitPrice(vo.getUnitPrice())
                .build();
        OrderJapEntity savedOrderJpaEntity = orderRepository.save(orderJapEntity);
        return OrderRes.from(savedOrderJpaEntity);
    }

    @Override
    public Iterable<OrderJapEntity> findAllByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public OrderRes getOrderByOrderId(String orderId) {
        return OrderRes.from(orderRepository.findByOrderId(orderId));
    }
}
