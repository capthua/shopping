package com.shopping.mqdemo1.model;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_order")
public class Order {
    private String id;
    private Date createTime;
    private Date modifyTime;
    private Double totalCost;
    private String paymentId;
    private String deliveryId;
    private Byte state;
    private String userId;
}
