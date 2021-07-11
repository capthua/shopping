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
        return orderService.saveVastOrders(num);
    }

    @PostMapping("list")
    public List<Order> listOrder(@RequestBody Order criteria) {
        return orderService.list(criteria);
    }
    @GetMapping("get")
    public Order getOrder(Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("update")
    public int update(Long id){
        Order value=new Order();
        value.setUserId("u3_1");
        Order criteria=new Order();
        criteria.setUserId("u3");
        return orderService.update(value,criteria);
    }

    @DeleteMapping("remove")
    public int removeOrder(Long id) {
        return orderService.remove(id);
    }
}

