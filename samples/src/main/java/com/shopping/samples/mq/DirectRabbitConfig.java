package com.shopping.samples.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * direct类型exchange routingKey匹配
 */
@Configuration
public class DirectRabbitConfig {

    static final String DIRECT_QUEUE_NAME="htDirectQueue";
    public static final String DIRECT_EXCHANGE_NAME="htDirectExchange";
    public static final String ROUTING_KEY="htDirectRouting";

    //发送消息是只需要创建exchange即可
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    //接收消息是需要声明队列，然后通过routingKey将队列与exchange绑定

    @Bean
    public Queue directQueue(){
        return new Queue(DIRECT_QUEUE_NAME,false);
    }

    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(ROUTING_KEY);
    }
}
