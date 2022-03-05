package com.gala.bug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceZipkin2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZipkin2Application.class, args);
	}


	private static final Logger log = LoggerFactory.getLogger(ServiceZipkin2Application.class);

	@Autowired
	RestTemplate restTemplate;
	@Value("${server.port}")
	String port;

	@RequestMapping("/")
	String home() {
		String msg = "Hello World from " + port;
		log.info(msg);
		return msg;
	}

	@RequestMapping("/hello")
	public String hello() {
		return restTemplate.getForObject("http://service-zipkin/",String.class);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
