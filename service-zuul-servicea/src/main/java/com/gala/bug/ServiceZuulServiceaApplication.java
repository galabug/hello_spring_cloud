package com.gala.bug;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@EnableEurekaClient
@RestController
@SpringBootApplication
public class ServiceZuulServiceaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServiceZuulServiceaApplication.class, args);
	}


	@Value("${server.port}")
	String port;

	@RequestMapping("/hello")
	public String home(@RequestParam String name) {
		String msg = "hello "+name+",i am zuul-servicea on:" +port;
		System.out.println(msg);
		return msg;
	}

}
