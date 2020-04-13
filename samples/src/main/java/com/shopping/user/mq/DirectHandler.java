package com.shopping.user.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RabbitListener(queues= DirectRabbitConfig.DIRECT_QUEUE_NAME)
public class DirectHandler {

    @RabbitHandler
    public void process(@Headers MessageHeaders headers, byte[] msgPayload){
        String msg=new String(msgPayload);
        log.info("DirectHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

}
