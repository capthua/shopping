package com.shopping.samples.mq.simple.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * direct特定的routingKey
 */
public class EmitLog {

    private static final String EXCHANGE_NAME = "logs_routing";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            for(int i=0;i<100;i++){
                String message= "hello world"+i;
                Thread.sleep(1000);
                channel.basicPublish(EXCHANGE_NAME, "error", null, (message+" error").getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(EXCHANGE_NAME, "info", null, (message+" info").getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(EXCHANGE_NAME, "warn", null, (message+" warn").getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
