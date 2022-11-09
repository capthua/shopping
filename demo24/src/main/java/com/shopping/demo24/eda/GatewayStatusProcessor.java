package com.shopping.demo24.eda;

import com.shopping.common.eda.EdaEventProcessor;
import com.shopping.common.eda.EventProcessor;
import com.shopping.common.eda.IotMessage;
import com.shopping.common.eda.MsgFactory;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@EdaEventProcessor(value = "GatewayStatusProcessor",manager = "msgUp",eventId = "gatewayStatus")
public class GatewayStatusProcessor extends EventProcessor<GatewayStatusMsg> {
    @Override
    protected void validateData(GatewayStatusMsg msg) {
        super.validateData(msg);
    }

    @Override
    protected Object bizProcess(GatewayStatusMsg msg) {
        return super.bizProcess(msg);
    }

    @Override
    protected void initFactory() {
        this.msgFactory=new GatewayStatusFactory();
    }
}
