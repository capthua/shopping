package com.shopping.dbdemo2.service;

import com.shopping.dbdemo2.model.Order;

import java.util.List;

public interface OrderService {
    int sendOrderMsg();
    int saveVastOrders(int num);

    Order getOrderById(String id);
    List<Order> list();

    int saveOrder();
    int saveOrderB();

    int remove(Long id);
}
