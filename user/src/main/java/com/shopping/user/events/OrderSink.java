package com.shopping.user.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderSink {

    /**
     * Input channel name.
     * rabbitmq中会自动创建一个"userChange"的exchange
     */
    String INPUT = "order";

    /**
     * @return input channel.
     */
    @Input(OrderSink.INPUT)
    SubscribableChannel input();
}
