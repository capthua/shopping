package com.shopping.order.service.impl;

import com.shopping.order.events.OrderSender;
import com.shopping.order.model.Order;
import com.shopping.order.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSender sender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public int saveOrder() {
        Order order=new Order();
        order.setId(23L);
        order.setName("hehe");
        sender.pushMsg(order);
        return 0;
    }
}
