package com.locals.locals_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.locals.locals_api")
public class LocalsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalsApiApplication.class, args);
	}

}
