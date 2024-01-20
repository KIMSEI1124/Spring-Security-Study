package com.devsei.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderReq {
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("unit_price")
    private Integer unitPrice;
}
