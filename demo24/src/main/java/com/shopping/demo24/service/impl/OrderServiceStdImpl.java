package com.shopping.demo24.service.impl;


import com.shopping.demo24.api.OrderServiceStd;
import com.shopping.demo24.api.model.OrderModel;
import com.shopping.demo24.dao.dataobject.OrderDO;
import com.shopping.demo24.dao.mapper.OrderMapperStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceStdImpl implements OrderServiceStd {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceStdImpl.class);

    @Autowired
    private OrderMapperStd orderMapper;

    @Override
    public int sendOrderMsg() {
        //Order order=new Order();
        //order.setId(23L);
        //order.setName("hehe");
        //sender.pushMsg(order);
        return 0;
    }


    @Cacheable("order")
    @Override
    public OrderModel getOrderById(Long id) {
        OrderDO orderDO = orderMapper.getById(id);
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(orderDO, orderModel);
        return orderModel;
    }

    @Override
    @Transactional
    public void setState(Long id, Byte status) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(id);
        orderDO.setState(status);
        orderDO.setModifyTime(System.currentTimeMillis());
        orderMapper.updateByPrimaryKeySelective(orderDO);
    }

    @Override
    public List<OrderModel> listOrder(OrderModel param) {
        orderMapper.delete(new OrderDO());

        List<OrderDO> orderDOs = orderMapper.selectAll();
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderDO orderDO : orderDOs) {
            OrderModel orderModel = new OrderModel();
            BeanUtils.copyProperties(orderDO, orderModel);
            orderModels.add(orderModel);
        }
        return orderModels;
    }
}
