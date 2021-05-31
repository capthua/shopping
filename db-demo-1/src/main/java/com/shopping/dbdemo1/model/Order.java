package com.shopping.dbdemo1.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    private Long id;
    private Date createTime;
    private Date modifyTime;
    private Double totalCost;
    private String paymentId;
    private String deliveryId;
    private Byte state;
    private String userId;
}
