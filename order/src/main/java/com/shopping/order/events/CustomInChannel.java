package com.shopping.order.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomInChannel {

    /**
     * Input channel name.
     */
    String INPUT = "userChange";

    /**
     * @return input channel.
     */
    @Input(CustomInChannel.INPUT)
    SubscribableChannel input();
}
