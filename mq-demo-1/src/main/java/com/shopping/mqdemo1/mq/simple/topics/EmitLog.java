package com.shopping.mqdemo1.mq.simple.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * direct特定的routingKey
 */
public class EmitLog {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            for(int i=0;i<100;i++){
                String message= "hello world"+i;
                Thread.sleep(1000);
//                channel.basicPublish(EXCHANGE_NAME, "quick.orange.rabbit", null, (message+" quick.orange.rabbit").getBytes(StandardCharsets.UTF_8));
//                channel.basicPublish(EXCHANGE_NAME, "quick.orange.fox", null, (message+" quick.orange.fox").getBytes(StandardCharsets.UTF_8));
//                channel.basicPublish(EXCHANGE_NAME, "lazy.brown.fox", null, (message+" lazy.brown.fox").getBytes(StandardCharsets.UTF_8));
//                channel.basicPublish(EXCHANGE_NAME, "quick.brown.fox", null, (message+" quick.brown.fox").getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(EXCHANGE_NAME, "lazy.orange.hehe", null, (message+" lazy.pink.rabbit").getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
