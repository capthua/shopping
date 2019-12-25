package com.shopping.order.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomInChannel.class)
public class UserChangeHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserChangeHandler.class);

    @StreamListener(CustomInChannel.INPUT)
    public void loggerSink(UserChange userChange){
        logger.info("Received a message:{}",userChange);
    }
}
