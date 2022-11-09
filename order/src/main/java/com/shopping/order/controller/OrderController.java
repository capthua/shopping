package com.shopping.order.controller;

import com.shopping.order.api.model.OrderModel;
import com.shopping.order.dao.dataobject.OrderDO;
import com.shopping.order.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("saveVastOrders")
    public Integer saveOrder(Integer num) {
        logger.info("saveVastOrders order");
        return orderService.saveVastOrders(num);
    }

    @GetMapping("getById")
    public OrderModel getById(Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("setState")
    public void setState(Long id,Byte status) {
        orderService.setState(id,status);
    }
}

