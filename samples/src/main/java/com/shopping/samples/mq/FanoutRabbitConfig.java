package com.shopping.samples.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    public static final String FANOUT_QUEUE_NAME="htFanoutQueue";
    static final String FANOUT_QUEUE1_NAME="htFanoutQueue1";
    public static final String FANOUT_EXCHANGE_NAME="htFanoutExchange";

    public static final String FANOUT="htFanoutRouting.r1";
    public static final String FANOUT1="htFanoutRouting.r2";


    //发送消息是只需要创建exchange即可
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }

    //接收消息是需要声明队列，然后通过routingKey将队列与exchange绑定

    @Bean
    public Queue fanoutQueue(){
        //durable:true mq服务器重启后让然存在 false重启后队列自动删除
        return new Queue(FANOUT_QUEUE_NAME,false);
    }

    /**
     * 队列绑定fanout类型的exchange是无法设置routingKey
     * @return
     */
    @Bean
    Binding bindingFanout(){
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    //topic1
    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE1_NAME,false);
    }
    @Bean
    Binding bindingFanout1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

}
