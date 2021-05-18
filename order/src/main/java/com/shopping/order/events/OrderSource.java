//package com.shopping.order.events;
//
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.messaging.MessageChannel;
//
//public interface OrderSource {
//    /**
//     * Name of the output channel.
//     * order对应的是binding,
//     * destination对应的是rabbitmq里的exchange,kafka中的topic.
//     * 如果没有设置destination, rabbitmq会自动创建一个和binding同名的exchange
//     */
//    String OUTPUT = "order";
//
//    /**
//     * @return output channel
//     */
//    @Output(OrderSource.OUTPUT)
//    MessageChannel output();
//}
