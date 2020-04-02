package com.shopping.order.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderOutChannel {
    /**
     * Name of the output channel.
     * rabbitmq中会自动创建一个"userChange"的exchange
     */
    String OUTPUT = "order";

    /**
     * @return output channel
     */
    @Output(OrderOutChannel.OUTPUT)
    MessageChannel output();
}
