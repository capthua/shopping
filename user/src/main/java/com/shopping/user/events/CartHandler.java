package com.shopping.user.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

@EnableBinding(CartSink.class)
public class CartHandler {

    private static final Logger logger = LoggerFactory.getLogger(CartHandler.class);


    /**
     * 参数也可以是对象，会自动将消息实例化 UserChange
     * @param headers
     * @param payload
     */
    @StreamListener(CartSink.INPUT)
    public void loggerSink(@Headers MessageHeaders headers, byte[] payload){
        String cartChange=new String(payload);
        logger.info("cart change:{}",cartChange);
    }
}
