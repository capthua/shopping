package com.shopping.demo24.vo;

import com.shopping.demo24.model.Order;
import com.shopping.demo24.model.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class OrderVO extends Order {

    private List<OrderItem> orderItems;

}
