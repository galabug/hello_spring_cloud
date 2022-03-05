package com.gala.bug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@EnableAutoConfiguration
public class ServiceZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZipkinApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(ServiceZipkinApplication.class);

	@Value("${server.port}")
	String port;

	@RequestMapping("/")
	String home() {
		String msg = "Hello World from " + port;
		log.info(msg);
		return msg;
	}
}
