package com.shopping.dbdemo1.service;

import com.shopping.dbdemo1.model.Order;

public interface OrderService {
    int sendOrderMsg();
    int saveVastOrders(int num);

    Order getOrderById(String id);

    int saveOrder();
}
