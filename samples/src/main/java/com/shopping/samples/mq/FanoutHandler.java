package com.shopping.samples.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@RabbitListener(queues = {FANOUT_QUEUE_NAME,FANOUT_QUEUE1_NAME})
public class FanoutHandler {

    @RabbitHandler
    public void process(@Headers MessageHeaders headers, byte[] msgPayload){
        String msg=new String(msgPayload);
        log.info("FanoutHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

    @RabbitHandler
    public void processStringMsg(@Headers MessageHeaders headers, String msg){
        log.info("FanoutHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

}
