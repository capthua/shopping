package com.shopping.dbdemo1.service;

import com.shopping.dbdemo1.model.Order;

import java.util.List;

public interface OrderService {
    int sendOrderMsg();
    int saveVastOrders(int num);

    Order getOrderById(String id);
    List<Order> list();

    int saveOrder();

    int remove(Long id);
}
