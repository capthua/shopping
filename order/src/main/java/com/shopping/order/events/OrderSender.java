//package com.shopping.order.events;
//
//import com.shopping.order.model.Order;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
////必须添加，要不然无法注入。也可以加在启动类上，可以重复添加
//@EnableBinding(OrderSource.class)
//@Component
//public class OrderSender {
//
//    @Autowired
//    private OrderSource orderSource;
//
//    private static final Logger logger= LoggerFactory.getLogger(OrderSender.class);
//
//    public void pushMsg(Order order){
//        logger.info("sending rabbitmq message:{}",order.toString());
//        orderSource.output().send(MessageBuilder.withPayload(order).build());
//    }
//}
