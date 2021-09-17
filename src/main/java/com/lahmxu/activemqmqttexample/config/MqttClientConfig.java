package com.lahmxu.activemqmqttexample.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * start mqtt server
 */
@Slf4j
@Configuration
@DependsOn("brokerService")
@RequiredArgsConstructor
public class MqttClientConfig {

    private static final String CLIENT_ID = "mqtt-client";

    private static String CLIENT_BROKER_URL = "tcp://127.0.0.1:1883";

    private final MqttClientCallback mqttClientCallback;

    @Bean
    public MqttClient mqttClient() {
        MqttClient client = null;
        try {
            client = new MqttClient(CLIENT_BROKER_URL, CLIENT_ID);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setAutomaticReconnect(true);
            connOpts.setMaxReconnectDelay(10);

            client.setCallback(mqttClientCallback);
            client.connect(connOpts);
            client.subscribe(ActiveMQConfig.TOPIC);
        } catch (MqttException e) {
            log.error("create mqtt client error : ", e);
            e.printStackTrace();
        }
        return client;

    }
}
