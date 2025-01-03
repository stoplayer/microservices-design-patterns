package com.msdp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroserveDesignPatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserveDesignPatternApplication.class, args);
	}

}
