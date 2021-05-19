package com.shopping.mqdemo2.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

@EnableBinding(OrderSink.class)
public class OrderHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderHandler.class);


    /**
     * 参数也可以是对象，会自动将消息实例化 UserChange
     * @param headers
     * @param payload
     */
    @StreamListener(OrderSink.INPUT)
    public void handleMsg(@Headers MessageHeaders headers, byte[] payload){
        String order=new String(payload);
        logger.info("Received a message:{}",order);
    }
}
