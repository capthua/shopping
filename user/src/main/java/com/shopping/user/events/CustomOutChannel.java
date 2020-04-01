package com.shopping.user.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomOutChannel {
    /**
     * Name of the output channel.
     * rabbitmq中会自动创建一个"userChange"的exchange
     */
    String OUTPUT = "userChange";

    /**
     * @return output channel
     */
    @Output(CustomOutChannel.OUTPUT)
    MessageChannel output();
}
