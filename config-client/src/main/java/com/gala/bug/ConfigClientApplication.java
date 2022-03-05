package com.gala.bug;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Value("${msg}")
	String msg;
	@Value("${app.clientdev}")
	String clientdev;

	@RequestMapping(value = "/hello")
	public String hello(){
		System.out.println(msg);
		System.out.println(clientdev);
		return clientdev+ " " +msg;
	}
}
