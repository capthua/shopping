package com.shopping.dbdemo2.service.impl;

//import com.shopping.order.events.OrderSender;

import com.shopping.dbdemo2.dao.OrderMapper;
import com.shopping.dbdemo2.model.Order;
import com.shopping.dbdemo2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderServiceImpl orderService;

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
            order.setCreateTime(currentTime);
            order.setModifyTime(currentTime);
            order.setTotalCost(55.2);
            order.setPaymentId("paymentId");
            order.setDeliveryId("deliveryId");
            order.setState((byte) 1);
            order.setUserId(genUserId());
            orders.add(order);
            orderMapper.insertUseGeneratedKeys(order);
        }
        logger.info("开始插入");
        long startTime=System.currentTimeMillis();
//        int result = orderMapper.insertList(orders);
        long useTime=System.currentTimeMillis()-startTime;
        logger.info("插入结束,用时:{}秒",useTime/1000);
        return 0;
    }

    private static String genUserId(){
        List<String> userIds =new ArrayList<>();
        for(int i=0;i<16;i++){
            userIds.add("u"+i);
        }
        return userIds.get(new Random().nextInt(16));
    }

    @Transactional
    public int saveOrder() {
        int a=22;
        saveOrderNotPublic();
        Date currentTime=new Date();
        Order order=new Order();
        order.setId(2233L);
        order.setCreateTime(currentTime);
        order.setModifyTime(currentTime);
        order.setTotalCost(55.2);
        order.setPaymentId("paymentId_a");
        order.setDeliveryId("deliveryId");
        order.setState((byte) 1);
        order.setUserId(genUserId());
        //只有这种方式，或者手动写insert才能成功
        return orderMapper.insertUseGeneratedKeys(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int saveOrderB() {
        Date currentTime=new Date();
        Order order=new Order();
        order.setId(2233L);
        order.setCreateTime(currentTime);
        order.setModifyTime(currentTime);
        order.setTotalCost(55.2);
        order.setPaymentId("paymentId_b");
        order.setDeliveryId("deliveryId");
        order.setState((byte) 1);
        order.setUserId(genUserId());
        //只有这种方式，或者手动写insert才能成功
        return orderMapper.insertUseGeneratedKeys(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int saveOrderNotPublic() {
        Date currentTime=new Date();
        Order order=new Order();
        order.setId(2233L);
        order.setCreateTime(currentTime);
        order.setModifyTime(currentTime);
        order.setTotalCost(55.2);
        order.setPaymentId("paymentId_b");
        order.setDeliveryId("deliveryId");
        order.setState((byte) 1);
        order.setUserId(genUserId());
        //只有这种方式，或者手动写insert才能成功
        return orderMapper.insertUseGeneratedKeys(order);
    }

    @Override
    public Order getOrderById(String id) {
        return orderMapper.getById(id);
    }

    @Override
    public int remove(Long id) {
        int result=0;
        if(id!=null){
            result=orderMapper.deleteByPrimaryKey(id);
        } else {
            result=orderMapper.delete(new Order());
        }
        return result;
    }

    @Override
    public List<Order> list() {
        return orderMapper.select(new Order());
    }
}
