package com.gala.bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableZipkinServer
public class ServerZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerZipkinApplication.class, args);
	}

}
