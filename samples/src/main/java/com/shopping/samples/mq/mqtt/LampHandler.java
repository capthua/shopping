package com.shopping.samples.mq.mqtt;

import com.shopping.samples.mq.TopicRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = {TopicRabbitConfig.MQTT_TOPIC_Q})
public class LampHandler {

    @RabbitHandler
    public void processStringMsg(@Headers MessageHeaders headers, String msg){
        log.info("LampHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

}
