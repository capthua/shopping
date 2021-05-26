package com.shopping.mqdemo1.mq;

import com.rabbitmq.client.Channel;
import com.shopping.samples.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

/**
 * 一个方法对应一个handler
 * @Component 必须加，要不然无法注册到mq
 */
@Slf4j
@Component
//@RabbitListener(queues = {TopicRabbitConfig.TOPIC_QUEUE_NAME})
public class TopicHandler {

    @RabbitHandler
    public void processByteMsg(@Headers MessageHeaders headers, byte[] msgPayload){
        String msg=new String(msgPayload);
        log.info("TopicHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

    @RabbitHandler
    public void processStringMsg(@Headers MessageHeaders headers, Message msg){
        log.info("TopicHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
    }

    @RabbitHandler
    public void processStringMsg(@Headers MessageHeaders headers, Channel channel, String msg, @Header(AmqpHeaders.DELIVERY_TAG) long tag)throws Exception{
        log.info("TopicHandler收到消息:{},exchange:{},routingKey:{},queue:{}",
                msg, headers.get("amqp_receivedExchange"),
                headers.get("amqp_receivedRoutingKey"),
                headers.get("amqp_consumerQueue"));
        //手动确认消息,如果Unacked的数目达到Prefetch count,就不给次消费者发送
//        channel.basicAck(tag,false);
    }

}
