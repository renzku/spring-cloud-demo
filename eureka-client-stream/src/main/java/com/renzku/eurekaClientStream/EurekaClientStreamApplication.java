package com.renzku.eurekaClientStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.SpringCloudBusClient;

@SpringBootApplication
public class EurekaClientStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientStreamApplication.class, args);
	}

}
