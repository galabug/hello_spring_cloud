package com.gala.bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMqApplication.class, args);
	}

}
