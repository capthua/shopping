package com.shopping.samples.service.impl;

import com.shopping.samples.service.LampService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class LampServiceImpl implements LampService {

    @Value("${mqtt.qos:1}")
    Integer qos;

    @Autowired
    MqttClient mqttClient;

    @Override
    public void turnOn(String name) {
        System.out.println("eheh");
        String msg="{\"cmd\":\"turnOn\"}";
        try {
            MqttMessage mqttMessage=new MqttMessage(msg.getBytes("utf8"));
            mqttMessage.setQos(qos);
            String topic="/room/lamp";
            mqttClient.publish(topic,mqttMessage);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return;
    }
}
