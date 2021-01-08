package com.shopping.device.mq.handler;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Component
public class LampMqttListener {

    MqttAsyncClient client;

    MqttCallbackExtended mqttCallbackExtended;

    public LampMqttListener(MqttAsyncClient client,MqttCallbackExtended mqttCallbackExtended){
        try {
            client.setCallback(mqttCallbackExtended);
            client.subscribe("room/lamp",1);
            if(!client.isConnected()){
                client.connect();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
