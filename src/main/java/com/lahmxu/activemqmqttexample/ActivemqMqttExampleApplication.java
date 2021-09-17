package com.lahmxu.activemqmqttexample;

import com.lahmxu.activemqmqttexample.biz.Order;
import com.lahmxu.activemqmqttexample.biz.OrderSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ActivemqMqttExampleApplication implements ApplicationRunner {

	private static Logger log = LoggerFactory.getLogger(ActivemqMqttExampleApplication.class);

	@Autowired
	private OrderSender orderSender;

	// send message
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		for (int i = 0; i < 3; i++){
			Order myMessage = new Order(Integer.toString(i) , new Date());
			orderSender.send(myMessage);
		}
		TimeUnit.SECONDS.sleep(3);
	}

	public static void main(String[] args) {
		SpringApplication.run(ActivemqMqttExampleApplication.class, args);
	}

}
