package com.shopping.device.mq.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hsh
 */
@Configuration
public class MqttProducerConfig {

    @Value("${mqtt.allow.anonymous}")
    private boolean allowAnonymous;

    @Value("${mqtt.auth.username}")
    private String authUsername;

    @Value("${mqtt.auth.password}")
    private String authPassword;

    @Value("${mqtt.set.clean.session}")
    private boolean setCleanSession;

    @Value("${mqtt.broker}")
    private String broker;

    private MqttConnectOptions producerConfigs() {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(setCleanSession);
        connOpts.setAutomaticReconnect(true);

        if (!allowAnonymous) {
            connOpts.setUserName(authUsername);
            connOpts.setPassword(authPassword.toCharArray());
        }
        return connOpts;
    }

    //@Bean
    public MqttClient mqttClient(MyMqttCallbackHandler myMqttCallbackHandler,
                                 @Value("${mqtt.consumerQos}") Integer qos) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient client = new MqttClient(broker, "device-client", persistence);
        client.setCallback(myMqttCallbackHandler);
        client.connect(producerConfigs());
        client.subscribe("room/lamp",qos);
        return client;
    }

    @Bean
    public MqttAsyncClient mqttAsyncClient(MyMqttCallbackHandler myMqttCallbackHandler,
                                           @Value("${mqtt.consumerQos}") Integer qos) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttAsyncClient asyncClient = new MqttAsyncClient(broker, "device-async-client",
                persistence);
        asyncClient.setCallback(myMqttCallbackHandler);
        IMqttToken token = asyncClient.connect(producerConfigs());
        token.waitForCompletion();
        asyncClient.subscribe("room/lamp",qos);
        return asyncClient;
    }

}
