package com.lahmxu.activemqmqttexample.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MqttClientCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("Mqtt disconnect: {}", throwable.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String receiveBodyStr = new String(mqttMessage.getPayload());
        log.info("[Mqtt receive msg] topic: {}, msg: {}", topic, receiveBodyStr);
        // TODO 业务处理消息
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
