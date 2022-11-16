package com.shopping.common.eda;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class ProcessorManagerImpl implements ProcessorManager {

    private final Map<String, EventProcessor> processors = new ConcurrentHashMap<>();

    @Override
    public boolean registerProcessor(String eventId, EventProcessor processor) {
        if (!processors.containsKey(eventId)) {
            processors.put(eventId, processor);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeProcessor(String eventId) {
        if (processors.containsKey(eventId)) {
            processors.remove(eventId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProcessor(String eventId, EventProcessor processor) {
        processors.put(eventId, processor);
        return true;
    }

    @Override
    public EventProcessor getProcessor(String eventId) {
        return processors.get(eventId);
    }

    @Override
    public Object process(String eventId, Object event) {
        if (processors.containsKey(eventId)) {
            return processors.get(eventId).process(event);
        }
        log.info("event:{}没有对应的processor", event);
        return null;
    }
}
