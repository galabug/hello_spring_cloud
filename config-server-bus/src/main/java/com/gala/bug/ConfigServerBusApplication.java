package com.gala.bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerBusApplication.class, args);
	}

}
