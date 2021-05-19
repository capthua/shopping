package com.shopping.mqdemo2.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CartSink {

    String INPUT = "cart";

    /**
     * @return input channel.
     */
    @Input(CartSink.INPUT)
    SubscribableChannel input();
}
