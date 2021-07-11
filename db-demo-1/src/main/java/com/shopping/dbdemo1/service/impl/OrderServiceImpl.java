package com.shopping.dbdemo1.service.impl;

//import com.shopping.order.events.OrderSender;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
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
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

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
        return num;
    }

    public int saveOrder(Order order) {
        Date currentTime=new Date();
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
    public Order getOrderById(Long id) {
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
    public List<Order> list(Order criteria) {
        return orderMapper.select(criteria);
    }

    @Override
    @Transactional
    public int update(Order value, Order orderCriteria) {
        Example exmple=new Example(Order.class);
        Criteria criteria=exmple.createCriteria();
        criteria.andEqualTo("userId",orderCriteria.getUserId());
//        return orderMapper.updateByExampleSelective(value,criteria);
//        return orderMapper.updateUserIdByUserId("u3_1","u3");
        List<Long> ids= Lists.newArrayList(620999153639993344L,620999153438666760L,620999153623216129L,620999153732268041L);
        int result = orderMapper.updateUserIdByIds("u3_5", Joiner.on(",").join(ids));
        //抛出异常，回滚
        int a=3/0;
        return result;
    }

    @Override
    public int updateUserIdByIds(String userId, List<Long> ids) {
        return orderMapper.updateUserIdByIds("u3_1", Joiner.on(",").join(ids));
    }

    private static String genUserId(){
        List<String> userIds =new ArrayList<>();
        for(int i=0;i<16;i++){
            userIds.add("u"+i);
        }
        return userIds.get(new Random().nextInt(16));
    }
}
