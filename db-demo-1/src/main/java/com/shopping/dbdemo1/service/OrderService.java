package com.shopping.dbdemo1.service;

import com.shopping.dbdemo1.model.Order;

import java.util.List;

public interface OrderService {

    int saveVastOrders(int num);

    Order getOrderById(Long id);

    List<Order> list(Order criteria);

    int saveOrder(Order order);

    /**
     * 删除，如果id为空，删除所有
     * @param id
     * @return
     */
    int remove(Long id);

    int update(Order value,Order criteria);

    int updateUserIdByIds(String userId,List<Long> ids);
}
