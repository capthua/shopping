package com.shopping.mqdemo1.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitTemplateConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        rabbitTemplate.setMandatory(true);

        //推送到server回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("ConfirmCallback correlationData:{},ack:{},cause:{}",correlationData,ack,cause));

        //消息返回给生产者, 路由不到队列时返回给发送者  先returnCallback,再 confirmCallback
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("ReturnCallback message:{},replyCode:{},replyText:{},exchange:{},routingKey:{}",
                    message,replyCode,replyText,exchange,routingKey);
        });

        return rabbitTemplate;
    }

}
