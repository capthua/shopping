package com.shopping.demo24.service.impl;

import com.shopping.demo24.dao.DictionaryMapper;
import com.shopping.demo24.dao.OrderMapper;
import com.shopping.demo24.model.Order;
import com.shopping.demo24.service.OrderServiceA;
import com.shopping.demo24.service.OrderServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceBImpl implements OrderServiceB {

    @Autowired
    OrderServiceA orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public void txTestB() {

    }

    /**
     * 测试读写分离
     *
     * @param order
     * @return
     */
    @Override
    @Transactional
    public Order saveOrder(Order order) {
        Order result = orderMapper.selectByPrimaryKey(order.getId());
        orderMapper.selectAll();
        if (result == null) {
            orderMapper.insertUseGeneratedKeys(order);
            result = order;
        }
        orderMapper.selectByPrimaryKey(order.getId());
        return result;
    }
}
