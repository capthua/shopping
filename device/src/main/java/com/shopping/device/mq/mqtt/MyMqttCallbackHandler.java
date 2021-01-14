package com.shopping.device.mq.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyMqttCallbackHandler implements MqttCallbackExtended {

    @Override
    public void connectComplete(boolean b, String s) {
        //mq服务器关闭后重启会触发此回调
        log.info("connectComplete b:{},s:{}",b,s);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.error("断开与服务器的连接",throwable);
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("messageArrived s:{},msg:{}",s,mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //订阅者没有ack也会回调

        //在publish中也可以设置
        //iMqttDeliveryToken.setActionCallback(mqttActionListener);
        log.info("deliveryComplete:{}",iMqttDeliveryToken);
    }
}
