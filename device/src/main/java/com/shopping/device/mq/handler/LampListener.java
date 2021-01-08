package com.shopping.device.mq.handler;

import com.rabbitmq.client.Channel;
import com.shopping.device.mq.consts.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author capthua
 */
@Slf4j
@Component
//@RabbitListener(queues = {Topics.MQTT_TOPIC_Q})
public class LampListener {

    @RabbitHandler
    public void processStringMsg(@Headers MessageHeaders headers, byte[] msgBytes, Channel channel,
                                 @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        String msg = new String(msgBytes, StandardCharsets.UTF_8);
        log.info("LampHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
        channel.basicAck(tag, false);
    }

}
