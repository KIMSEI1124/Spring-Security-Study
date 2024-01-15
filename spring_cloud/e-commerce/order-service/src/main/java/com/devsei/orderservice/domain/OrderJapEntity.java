package com.devsei.orderservice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class OrderJapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false, updatable = false)
    private LocalDate createAt;

    @Builder
    public OrderJapEntity(String productId, Integer quantity, Integer unitPrice, String userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = quantity * unitPrice;
        this.userId = userId;
        this.orderId = UUID.randomUUID().toString();
        this.createAt = LocalDate.now();
    }
}
