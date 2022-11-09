package com.shopping.order.api.model;

import lombok.Data;

@Data
public class OrderItemModel {
    private Long id;
    private Long orderId;
    private Long goodsId;
    private Integer quantity;
}
