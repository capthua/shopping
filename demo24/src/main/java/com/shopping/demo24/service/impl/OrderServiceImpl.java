package com.shopping.demo24.service.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.shopping.demo24.dao.OrderItemMapper;
import com.shopping.demo24.dao.OrderMapper;
import com.shopping.demo24.model.Order;
import com.shopping.demo24.model.OrderItem;
import com.shopping.demo24.service.OrderServiceA;
import com.shopping.demo24.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.*;


@Service
public class OrderServiceImpl implements OrderServiceA {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Transactional
    @Override
    public int saveVastOrders(int num) {
        List<Order> orders = new ArrayList<>();
        Date currentTime = new Date();
        for (int i = 0; i < num; i++) {
            Order order = new Order();
            orders.add(order);
            orderMapper.insertUseGeneratedKeys(order);
        }
        return num;
    }

    public int saveOrder(Order order) {
        //只有这种方式，或者手动写insert才能成功
        return orderMapper.insertUseGeneratedKeys(order);

    }

    @Override
    @Transactional
    public int saveOrder(OrderVO orderVO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderVO, order);
        order.setStatus(1);
        orderMapper.insertUseGeneratedKeys(order);
        List<OrderItem> items = orderVO.getOrderItems();
        items.forEach(item -> item.setOrderId(order.getId()));
        items.forEach(item -> orderItemMapper.insertUseGeneratedKeys(item));
        return 0;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.getById(id);
    }

    @Override
    public OrderVO getOrderDetail(Long id) {

        //绑定表测试
        List<Map> orders = orderItemMapper.getOrder(id.toString() + ",1");
        List<Map> map = orderItemMapper.getOrderDetails(id.toString() + ",2");

        List<Map> orders2 = orderItemMapper.getOrder(id.toString() + ",1");
        List<Map> map2 = orderItemMapper.getOrderDetails(id.toString() + ",2");

        Order order = orderMapper.getById(id);
        List<OrderItem> items = orderItemMapper.listItems(id);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setOrderItems(items);
        return orderVO;
    }

    @Override
    public int remove(Long id) {
        int result = 0;
        if (id != null) {
            result = orderMapper.deleteByPrimaryKey(id);
        } else {
            result = orderMapper.delete(new Order());
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
        Example exmple = new Example(Order.class);
        Criteria criteria = exmple.createCriteria();
        criteria.andEqualTo("userId", orderCriteria.getUserId());
//        return orderMapper.updateByExampleSelective(value,criteria);
//        return orderMapper.updateUserIdByUserId("u3_1","u3");
        List<Long> ids = Lists.newArrayList(620999153639993344L, 620999153438666760L, 620999153623216129L, 620999153732268041L);
        int result = orderMapper.updateUserIdByIds("u3_5", Joiner.on(",").join(ids));
        //抛出异常，回滚
        int a = 3 / 0;
        return result;
    }

    @Override
    public int updateUserIdByIds(String userId, List<Long> ids) {
        return orderMapper.updateUserIdByIds("u3_1", Joiner.on(",").join(ids));
    }

    private static String genUserId() {
        List<String> userIds = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            userIds.add("u" + i);
        }
        return userIds.get(new Random().nextInt(16));
    }
}
