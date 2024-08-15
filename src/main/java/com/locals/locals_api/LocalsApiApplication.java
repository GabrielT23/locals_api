package com.locals.locals_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Locals API",
		description = "API Reponsável por Cadastrar lugares.",
		version="1"
	)
)
@SecurityScheme(name="jwt_auth", scheme="bearer", bearerFormat="JWT", type=SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@ComponentScan(basePackages="com.locals.locals_api")
public class LocalsApiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("spring.datasource.url", dotenv.get("DATABASE_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DATABASE_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DATABASE_PASSWORD")); 
		SpringApplication.run(LocalsApiApplication.class, args);
	}

}
