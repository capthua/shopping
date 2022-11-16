package com.shopping.common.eda;

public interface ProcessorManager {

    /**
     * @param eventId
     * @param processor
     * @return
     */
    boolean registerProcessor(String eventId, EventProcessor processor);

    boolean removeProcessor(String eventId);

    boolean updateProcessor(String eventId, EventProcessor processor);

    EventProcessor getProcessor(String eventId);

    Object process(String eventId, Object event);

}
