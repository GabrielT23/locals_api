package com.locals.locals_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Locals API",
		description = "API Reponsável por Cadastrar lugares.",
		version="1"
	)
)
@ComponentScan(basePackages="com.locals.locals_api")
public class LocalsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalsApiApplication.class, args);
	}

}
