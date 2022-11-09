package com.shopping.demo24.api;


import com.shopping.demo24.api.model.OrderModel;

import java.util.List;

public interface OrderServiceStd {
    int sendOrderMsg();

    OrderModel getOrderById(Long id);

    void setState(Long id, Byte status);

    List<OrderModel> listOrder(OrderModel param);
}
