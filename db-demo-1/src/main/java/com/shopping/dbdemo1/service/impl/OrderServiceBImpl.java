package com.shopping.dbdemo1.service.impl;

import com.shopping.dbdemo1.service.OrderService;
import com.shopping.dbdemo1.service.OrderServiceB;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceBImpl implements OrderServiceB {

    @Autowired
    OrderService orderService;

    @Override
    public void txTestB() {

    }
}
