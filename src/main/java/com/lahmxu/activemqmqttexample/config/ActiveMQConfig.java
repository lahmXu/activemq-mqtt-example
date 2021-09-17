package com.lahmxu.activemqmqttexample.config;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQConfig {

    public static final String TOPIC = "upload";

    /**
     * 内置的模拟ActiveMQ Broker端口, 默认 1883
     */
    private static String BROKER_URL = "mqtt://127.0.0.1:1883";

    /**
     * Spring Embedded ActiveMQ Broker (Server)
     */
    @Bean
    BrokerService brokerService() throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector(BROKER_URL);
        brokerService.setPersistent(false);
        return brokerService;
    }
}