package com.shopping.demo24.controller;

import com.shopping.demo24.api.OrderServiceStd;
import com.shopping.demo24.api.model.OrderModel;
import com.shopping.demo24.model.Order;
import com.shopping.demo24.service.OrderServiceA;
import com.shopping.demo24.service.OrderServiceB;
import com.shopping.demo24.vo.OrderVO;
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
    OrderServiceA orderServiceA;
    @Autowired
    OrderServiceB orderServiceB;
    @Autowired
    OrderServiceStd orderService;


    @GetMapping("getById")
    public OrderModel getById(Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("setState")
    public void setState(@RequestBody OrderModel orderModel) {
        orderService.setState(orderModel.getId(), orderModel.getState());
    }

    @GetMapping("listOrder")
    public List<OrderModel> listOrder(OrderModel orderModel) {
        return orderService.listOrder(null);
    }


    @GetMapping("saveVast")
    public Integer saveOrder(Integer num) {
        return orderServiceA.saveVastOrders(num);
    }

    @PostMapping("save")
    public Integer saveOrder(@RequestBody OrderVO orderVO) {
        return orderServiceA.saveOrder(orderVO);
    }

    @PostMapping("getAndSave")
    public Order saveOrder(@RequestBody Order order) {
        return orderServiceB.saveOrder(order);
    }

    @PostMapping("list")
    public List<Order> listOrder(@RequestBody Order criteria) {
        return orderServiceA.list(criteria);
    }

    @GetMapping("get")
    public Order getOrder(Long id) {
        return orderServiceA.getOrderById(id);
    }

    @GetMapping("getOrderDetail")
    public Order getOrderDetail(Long id) {
        return orderServiceA.getOrderDetail(id);
    }

    @PostMapping("update")
    public int update(Long id) {
        Order value = new Order();
        value.setUserId(223L);
        Order criteria = new Order();
        criteria.setUserId(223L);
        return orderServiceA.update(value, criteria);
    }

    @DeleteMapping("remove")
    public int removeOrder(Long id) {
        return orderServiceA.remove(id);
    }
}

