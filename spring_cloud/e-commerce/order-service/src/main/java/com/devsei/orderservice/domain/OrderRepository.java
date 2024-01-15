package com.devsei.orderservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderJapEntity, Long> {
    OrderJapEntity findByOrderId(String orderId);

    Iterable<OrderJapEntity> findByUserId(String userId);
}
