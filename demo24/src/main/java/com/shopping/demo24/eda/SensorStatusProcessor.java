package com.shopping.demo24.eda;

import com.shopping.common.eda.EdaEventProcessor;
import com.shopping.common.eda.EventProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@EdaEventProcessor(value = "SensorStatusProcessor",manager = "msgUp",eventId = "sensorStatus")
public class SensorStatusProcessor extends EventProcessor<SensorStatusMsg> {

    @Override
    protected void validateData(SensorStatusMsg msg) {
        System.out.println("SensorStatusProcessor校验数据");
    }

    @Override
    protected Object bizProcess(SensorStatusMsg msg) {
        System.out.println("SensorStatusProcessor处理业务");
        return super.bizProcess(msg);
    }

    @Override
    protected boolean validateSensorMac() {
        return false;
    }

    @Override
    protected void initFactory() {
        this.msgFactory=new SensorStatusFactory();
    }
}
