package com.shopping.demo24.eda;

import com.shopping.common.eda.MsgFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SensorStatusFactory implements MsgFactory<SensorStatusMsg> {
    @Override
    public SensorStatusMsg getMsg(Object origin) {
        SensorStatusMsg msg=new SensorStatusMsg();
        log.info("SensorStatus工厂, orgin:{},return:{}", origin, msg);
        return msg;

    }
}
