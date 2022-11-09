package com.shopping.demo24.eda;

import com.shopping.common.eda.MsgFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GatewayStatusFactory implements MsgFactory<GatewayStatusMsg> {
    @Override
    public GatewayStatusMsg getMsg(Object origin) {
        GatewayStatusMsg msg=new GatewayStatusMsg();
        log.info("GatewayStatus工厂, orgin:{},return:{}", origin, msg);
        return msg;
    }
}
