package com.shooping.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO implements Serializable {

    /**
     * 订单编号
     */
    private String orderNo;
    private Long userId;
    /**
     * 订单项
     */
    List<OrderItemDTO> items;
    /**
     * 订单价格
     */
    private BigDecimal amount;

}
