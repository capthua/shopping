package com.shopping.order.common.mq.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues=DirectRabbitConfig.DIRECT_QUEUE_NAME)
public class DirectHandler {

    @RabbitHandler
    public void process(String msg){
        log.info("direct收到消息:{}",msg);
    }

}
