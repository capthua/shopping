package com.shopping.user.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomOutChannel {
    /**
     * Name of the output channel.
     */
    String OUTPUT = "userChange";

    /**
     * @return output channel
     */
    @Output(CustomOutChannel.OUTPUT)
    MessageChannel output();
}
