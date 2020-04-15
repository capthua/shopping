package com.shopping.user.mq.simple.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
    private final static String QUEUE_NAME = "hello_han";

    public static void main(String[] args) {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection=factory.newConnection();
            Channel channel=connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for(int i=0;i<10000;i++){
                String message = "Hello World!";
                message+=i;
                Thread.sleep(1000);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
