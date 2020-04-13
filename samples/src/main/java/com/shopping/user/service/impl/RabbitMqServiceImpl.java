package com.shopping.user.service.impl;

import com.shopping.user.model.Message;
import com.shopping.user.mq.TopicRabbitConfig;
import com.shopping.user.service.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.shopping.user.mq.FanoutRabbitConfig.FANOUT_QUEUE_NAME;

@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void test(){
        Message msg=new Message();
        msg.setId(System.currentTimeMillis());
        msg.setName(UUID.randomUUID().toString());
//        rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_EXCHANGE_NAME,DirectRabbitConfig.ROUTING_KEY,order.toString());
//
//        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE_NAME,"htTopicRouting.hehe",msg.toString()+TopicRabbitConfig.TOPIC);
//        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE_NAME,TopicRabbitConfig.TOPIC1,order.toString()+TopicRabbitConfig.TOPIC1);

//        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE_NAME,"hehe",order.toString()+"hehe");
//        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE_NAME,"haha",order.toString()+"hehe");

        //如果向默认的exchange中发送，则与routingKey同名的队列将收到消息
//        rabbitTemplate.convertAndSend("",FANOUT_QUEUE_NAME,msg.toString()+FANOUT_QUEUE_NAME);

        //回调测试
//        rabbitTemplate.convertAndSend("nonExistExchange","hehe",msg);
//        rabbitTemplate.convertAndSend("nonQExchange","hehe",msg);
//        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE_NAME,"heehda",msg.toString()+TopicRabbitConfig.TOPIC);

//        消息确认测试
        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE_NAME,"htTopicRouting.hehe",msg.toString()+TopicRabbitConfig.TOPIC);

    }

}
