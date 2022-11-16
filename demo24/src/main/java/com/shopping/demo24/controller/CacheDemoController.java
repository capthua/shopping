package com.shopping.demo24.controller;

import com.shopping.demo24.api.CacheDemoService;
import com.shopping.demo24.api.OrderServiceStd;
import com.shopping.demo24.api.model.OrderModel;
import com.shopping.demo24.model.Order;
import com.shopping.demo24.service.OrderServiceA;
import com.shopping.demo24.service.OrderServiceB;
import com.shopping.demo24.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@RestController
@RequestMapping("cache/order")
public class CacheDemoController {

    private static final Logger logger = LoggerFactory.getLogger(CacheDemoController.class);

    @Autowired
    CacheManager cacheManager;

    @Autowired
    private CacheDemoService cacheDemoService;


    @GetMapping("getById")
    public OrderModel getById(Long id) {
//        cacheDemoService.getOrderBById(id);
        return cacheDemoService.getOrderById(id);
    }

    @PostMapping("setState")
    public void setState(@RequestBody OrderModel orderModel) {
        cacheDemoService.setState(orderModel.getId(), orderModel.getState());
    }


}

