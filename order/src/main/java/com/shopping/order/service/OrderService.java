package com.shopping.order.service;

import com.shopping.order.model.Order;

import java.util.List;

public interface OrderService {
    int sendOrderMsg();
    int saveVastOrders(int num);

    Order getOrderById(String id);
}
