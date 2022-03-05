package com.bug.bug;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope//手动刷新配置文件信息
public class ConfigClientNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientNewApplication.class, args);
	}

//	@Value("${app.new}")
	String appnew;
	@Value("${server.port}")
	String msg;

	@RequestMapping(value = "/hello")
	public String hello(){
		System.out.println(appnew);
		System.out.println(msg);
		return appnew+"  "+msg;
	}

}
