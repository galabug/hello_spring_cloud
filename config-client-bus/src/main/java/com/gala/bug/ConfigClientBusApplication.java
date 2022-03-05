package com.gala.bug;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@RefreshScope//手动刷新配置文件信息
public class ConfigClientBusApplication {

	public static void main(String[] args) { SpringApplication.run(ConfigClientBusApplication.class, args); }

	@Value("${app.bus}")
	String appbus;
	@Value("${msg}")
	String msg;
	@RequestMapping(value = "/hello")
	public String hello(){
		System.out.println(appbus);
		System.out.println(msg);
		return appbus+"  "+msg;
	}

}
