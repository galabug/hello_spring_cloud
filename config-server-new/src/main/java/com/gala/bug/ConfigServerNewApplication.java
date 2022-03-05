package com.gala.bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@RestController
@EnableEurekaClient
public class ConfigServerNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerNewApplication.class, args);
	}

	@RequestMapping(value = "/hello")
	public String hello(){
		System.out.println("hello");
		return "hello";
	}
}
