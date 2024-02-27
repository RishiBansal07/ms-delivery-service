package com.delivery.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "com.delivery.project.configuration.feignClient")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
