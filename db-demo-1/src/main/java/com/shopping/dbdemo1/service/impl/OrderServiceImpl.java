package com.shopping.dbdemo1.service.impl;

//import com.shopping.order.events.OrderSender;
import com.google.common.base.Splitter;
import com.shopping.dbdemo1.config.db.manualrwsplitting.ReadOnly;
import com.shopping.dbdemo1.dao.OrderMapper;
import com.shopping.dbdemo1.model.Order;
import com.shopping.dbdemo1.service.OrderService;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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

    public int saveOrder() {
        Date currentTime=new Date();
        Order order=new Order();
        order.setCreateTime(currentTime);
        order.setModifyTime(currentTime);
        order.setTotalCost(55.2);
        order.setPaymentId("paymentId");
        order.setDeliveryId("deliveryId");
        order.setState((byte) 1);
        order.setUserId(genUserId());
        //只有这种方式，或者手动写insert才能成功
        return orderMapper.insertUseGeneratedKeys(order);

    }

    @Override
    @ReadOnly
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
