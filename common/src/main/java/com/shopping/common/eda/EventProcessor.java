package com.shopping.common.eda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 模板方法模式
 * 因为此类中的公共方法需要操纵数据库，所以需要注入一些dao，不能声明为抽象类。否则需要声明为抽象类
 *
 * @param <T>
 */
@Slf4j
@Component
public class EventProcessor<T> {

    protected MsgFactory msgFactory;

    Object process(Object event) {
        initFactory();
        T msg = (T) msgFactory.getMsg(event);
        validateMac(msg);
        validateData(msg);
        bizProcess(msg);
        save(msg);
        return null;
    }

    /**
     * 公共方法
     *
     * @param event
     */
    private void validateMac(T event) {
        System.out.println("校验网关mac地址");
        if (validateSensorMac()) {
            System.out.println("校验传感器Mac地址");
        }
    }

    /**
     * 钩子
     *
     * @return
     */
    protected boolean validateSensorMac() {
        return true;
    }

    protected void validateData(T event) {
    }

    protected Object bizProcess(T event) {
        return null;
    }

    private void save(Object data) {
        System.out.println("数据保存");
    }

    /**
     * 默认的工厂
     */
    protected void initFactory() {
        this.msgFactory = origin -> {
            log.info("默认工厂, orgin:{},return:{}", origin, origin);
            return origin;
        };
    }

}
