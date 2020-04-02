package com.shopping.user.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderInChannel {

    /**
     * Input channel name.
     * rabbitmq中会自动创建一个"userChange"的exchange
     */
    String INPUT = "userChange";

    /**
     * @return input channel.
     */
    @Input(OrderInChannel.INPUT)
    SubscribableChannel input();
}
