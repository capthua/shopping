package com.shopping.samples.mq.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.springframework.stereotype.Component;

/**
 * @author capthua
 */
@Slf4j
@Component
public class MqttActionListener implements IMqttActionListener {
    @Override
    public void onSuccess(IMqttToken iMqttToken) {
        log.info("success");
    }

    @Override
    public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
        log.info("failure");
    }
}
