package com.shopping.demo24.eda;

import com.shopping.common.eda.EdaEventProcessor;
import com.shopping.common.eda.EventProcessor;
import com.shopping.common.eda.IotMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EdaEventProcessor(value = "p3",manager = "msgDown",eventId = "3")
public class Processor3 extends EventProcessor<IotMessage>{
    @Override
    protected void validateData(IotMessage event) {
        super.validateData(event);
    }

    @Override
    protected Object bizProcess(IotMessage event) {
        return super.bizProcess(event);
    }
}
