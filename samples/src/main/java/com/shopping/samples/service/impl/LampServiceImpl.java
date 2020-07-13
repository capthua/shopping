package com.shopping.samples.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shopping.samples.service.LampService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class LampServiceImpl implements LampService {

    @Value("${mqtt.qos}")
    Integer qos;

    @Autowired
    MqttClient mqttClient;

    @Override
    public void turnOn(String name) {
        UUID uuid=UUID.randomUUID();
        JSONObject msgJson=new JSONObject();
        msgJson.put("id",uuid.toString());
        msgJson.put("cmd","on");
        String msg=msgJson.toJSONString();
        log.info("开灯:{}",msg);
        try {
            MqttMessage mqttMessage=new MqttMessage(msg.getBytes("utf8"));
            mqttMessage.setQos(qos);
            String topic="room/lamp";
            mqttClient.publish(topic,mqttMessage);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
