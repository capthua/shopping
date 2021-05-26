package com.shopping.dbdemo1.service.impl;

//import com.shopping.order.events.OrderSender;
import com.shopping.dbdemo1.dao.OrderMapper;
import com.shopping.dbdemo1.model.Order;
import com.shopping.dbdemo1.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int sendOrderMsg() {
        //Order order=new Order();
        //order.setId(23L);
        //order.setName("hehe");
        //sender.pushMsg(order);
        return 0;
    }

    @Transactional
    @Override
    public int saveVastOrders(int num) {
        List<Order> orders=new ArrayList<>();
        Date currentTime=new Date();
        for(int i=0;i<num;i++){
            Order order=new Order();
            order.setId(i+"");
            order.setCreateTime(currentTime);
            order.setModifyTime(currentTime);
            order.setTotalCost(55.2);
            order.setPaymentId("paymentId");
            order.setDeliveryId("deliveryId");
            order.setState((byte) 1);
            order.setUserId("userId");
            orders.add(order);
        }
        logger.info("开始插入");
        long startTime=System.currentTimeMillis();
        int result = orderMapper.insertList(orders);
        long useTime=System.currentTimeMillis()-startTime;
        logger.info("插入结束,用时:{}秒",useTime/1000);
        return result;
    }

    @Override
    public Order getOrderById(String id) {
        return orderMapper.getById(id);
    }
}
