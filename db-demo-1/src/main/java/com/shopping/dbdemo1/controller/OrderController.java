package com.shopping.dbdemo1.controller;

import com.shopping.dbdemo1.model.Order;
import com.shopping.dbdemo1.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("saveVast")
    public Integer saveOrder(Integer num) {
//        logger.info("saveVastOrders order");
        return orderService.saveVastOrders(num);
    }

    @GetMapping("list")
    public List<Order> listOrder(String id) {
        return orderService.list();
    }
    @GetMapping("get")
    public Order getOrder(String id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("remove")
    public int removeOrder(Long id) {
        return orderService.remove(null);
    }
}

