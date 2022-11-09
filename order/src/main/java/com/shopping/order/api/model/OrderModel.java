package com.shopping.order.api.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderModel {
    private Long id;
    private BigDecimal totalCost;
    private Long createTime;
    private Long modifyTime;
    private Long paymentId;
    private Byte state;
    private Long userId;

    List<OrderItemModel> orderItems;
}
