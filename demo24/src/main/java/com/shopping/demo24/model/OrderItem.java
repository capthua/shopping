package com.shopping.demo24.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_order_item")
public class OrderItem {

    @Id
    private Long id;
    private Long orderId;
    private String productName;

}
