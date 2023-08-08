package com.shopping.order.api;

import com.shooping.api.dto.OrderDTO;
import com.shopping.common.response.ObjectResponse;
import com.shopping.order.api.model.OrderModel;

public interface OrderService {
    int sendOrderMsg();

    int saveVastOrders(int num);

    OrderModel getOrderById(Long id);

    void setState(Long id, Byte status);

    void saveOrder(OrderModel order);

    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
