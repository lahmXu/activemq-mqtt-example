package com.lahmxu.activemqmqttexample.biz;

import com.alibaba.fastjson.JSON;
import com.lahmxu.activemqmqttexample.config.ActiveMQConfig;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSender {
    private static Logger log = LoggerFactory.getLogger(OrderSender.class);

    private final MqttClient mqttClient;

    public void send(Order order) throws MqttException {
        log.info("[Send message] <{}>", order);

        MqttMessage mqttMessage = new MqttMessage();
        //mqttMessage.setQos(2);
        mqttMessage.setPayload(JSON.toJSONString(order).getBytes());
        mqttClient.publish(ActiveMQConfig.TOPIC, mqttMessage);
    }
}
