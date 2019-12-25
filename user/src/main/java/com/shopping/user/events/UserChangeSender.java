package com.shopping.user.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

//必须添加，要不然无法注入。也可以加在启动类上，可以重复添加
@EnableBinding(CustomOutChannel.class)
@Component
public class UserChangeSender {

    @Autowired
    private CustomOutChannel outChannel;

    private static final Logger logger= LoggerFactory.getLogger(UserChangeSender.class);

    public void pushMsg(UserChange userChange){
        logger.info("sending rabbitmq message:{}",userChange.toString());
        outChannel.output().send(MessageBuilder.withPayload(userChange).build());
    }
}
