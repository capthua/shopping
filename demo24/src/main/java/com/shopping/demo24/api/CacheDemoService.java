package com.shopping.demo24.api;


import com.shopping.demo24.api.model.OrderModel;

import java.util.List;

public interface CacheDemoService {

    OrderModel getOrderById(Long id);

    OrderModel getOrderBById(Long id);

    OrderModel setState(Long id, Byte status);

}
