package com.shopping.demo24.api.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderModel implements Serializable {

    private static final long serialVersionUID = 24L;

    private Long id;
    private Date createTime;
    private Date modifyTime;
    private BigDecimal totalCost;
    private Long paymentId;
    private Long deliveryId;
    private Byte state;
    private Long userId;
}
