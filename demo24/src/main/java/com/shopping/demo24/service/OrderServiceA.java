package com.shopping.demo24.service;

import com.shopping.demo24.model.Order;
import com.shopping.demo24.vo.OrderVO;

import java.util.List;

public interface OrderServiceA {

    int saveVastOrders(int num);

    Order getOrderById(Long id);

    OrderVO getOrderDetail(Long id);

    List<Order> list(Order criteria);

    int saveOrder(Order order);

    int saveOrder(OrderVO orderVO);

    /**
     * 删除，如果id为空，删除所有
     *
     * @param id
     * @return
     */
    int remove(Long id);

    int update(Order value, Order criteria);

    int updateUserIdByIds(String userId, List<Long> ids);
}
