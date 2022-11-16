package com.shopping.demo24.service.impl;

import com.shooping.api.service.order.OrderParam;
import com.shooping.api.service.order.OrderRpcService;
import com.shopping.common.response.ObjectResponse;
import com.shopping.demo24.service.DubboDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class DubboDemoServiceImpl implements DubboDemoService {

    @DubboReference(timeout = 20000, version = "0.24", async = false, check = false)
    private OrderRpcService orderService;


    @Override
    public void dubboTest1() {
        OrderParam orderParam = new OrderParam();
        orderParam.setOrderId(12L);
        ObjectResponse result = orderService.getOrder(orderParam);
        System.out.println("hehe");
        ////异步调用
        //orderService.getOrder(orderParam);
        ////拿到调用的Future引用，当结果返回后，会被通知和设置到此Future
        //CompletableFuture<String> resultFuture = RpcContext.getContext().getCompletableFuture();
        //// 为Future添加回调
        //resultFuture.whenComplete((retValue, exception) -> {
        //    if (exception == null) {
        //        System.out.println(retValue);
        //    } else {
        //        exception.printStackTrace();
        //    }
        //});

        //CompletableFuture<String> future = RpcContext.getContext().asyncCall(
        //        () -> {
        //            orderService.getOrder(orderParam);
        //        }
        //);
        //
        //future.get();
    }
}
