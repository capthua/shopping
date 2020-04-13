package com.shopping.user.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import static com.shopping.user.mq.TopicRabbitConfig.TOPIC_QUEUE1_NAME;

/**
 * 一个方法对应一个handler
 */
@Slf4j
@Component
//@RabbitListener(queues = {TOPIC_QUEUE1_NAME})
public class TopicHandler2 {

    @RabbitHandler
    public void processByteMsg(@Headers MessageHeaders headers, byte[] msgPayload){
        String msg=new String(msgPayload);
        log.info("TopicHandler2收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

    @RabbitHandler
    public void processStringMsg(@Headers MessageHeaders headers, String msg){
        log.info("TopicHandler2收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

}
