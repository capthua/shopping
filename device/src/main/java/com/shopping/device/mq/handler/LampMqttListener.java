package com.shopping.device.mq.handler;

import com.shopping.device.mq.mqtt.MyMqttCallbackHandler;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Component
public class LampMqttListener {

    MqttAsyncClient client;

    MyMqttCallbackHandler myMqttCallbackHandler;

    public LampMqttListener(MqttAsyncClient client,MyMqttCallbackHandler myMqttCallbackHandler){
        try {
            client.setCallback(myMqttCallbackHandler);
            client.subscribe("room/lamp",1);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
