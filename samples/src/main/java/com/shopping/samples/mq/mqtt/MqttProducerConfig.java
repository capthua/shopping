package com.shopping.samples.mq.mqtt;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
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

    @Bean
    public MqttClient mqttClient() throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient = new MqttClient(broker, MqttClient.generateClientId(), persistence);
        sampleClient.connect(producerConfigs());
        return sampleClient;
    }

}
