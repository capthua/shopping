package com.shopping.samples.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shopping.samples.mq.mqtt.MqttActionListener;
import com.shopping.samples.mq.mqtt.MyMqttCallbackHandler;
import com.shopping.samples.service.LampService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class LampServiceImpl implements LampService {

    @Value("${mqtt.producerQos}")
    private Integer qos;

    @Autowired
    MqttAsyncClient mqttAsyncClient;

    @Autowired
    MqttActionListener mqttActionListener;

    @Autowired
    MyMqttCallbackHandler myMqttCallbackHandler;

    @Override
    public void turnOn(String name) {
        UUID uuid=UUID.randomUUID();
        JSONObject msgJson=new JSONObject();
        msgJson.put("id",uuid.toString());
        msgJson.put("cmd","on");
        String msg=msgJson.toJSONString();
        log.info("开灯:{}",msg);
        try {
            MqttMessage mqttMessage=new MqttMessage(msg.getBytes(StandardCharsets.UTF_8));
            mqttMessage.setQos(qos);
            //mqttMessage.setQos(1);
            String topic="room/lamp";
            //mqttClient.setCallback(myMqttCallbackHandler);
            //mqttClient.publish(topic,mqttMessage);
            mqttAsyncClient.setCallback(myMqttCallbackHandler);
            //mqttAsyncClient.publish(topic,mqttMessage);
            mqttAsyncClient.publish(topic,mqttMessage,null,mqttActionListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
