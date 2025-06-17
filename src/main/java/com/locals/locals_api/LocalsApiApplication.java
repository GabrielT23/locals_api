package com.locals.locals_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.web.context.WebServerApplicationContext;

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
        description = "API Responsável por Cadastrar lugares.",
        version = "1"
    )
)
@SecurityScheme(
    name = "jwt_auth",
    scheme = "bearer",
    bearerFormat = "JWT",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER
)
@ComponentScan(basePackages = "com.locals.locals_api")
public class LocalsApiApplication implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(LocalsApiApplication.class);

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.datasource.url", dotenv.get("DATABASE_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DATABASE_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DATABASE_PASSWORD"));
        SpringApplication.run(LocalsApiApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Cast para acessar o WebServer e obter a porta
        if (event.getApplicationContext() instanceof WebServerApplicationContext wsContext) {
            int port = wsContext.getWebServer().getPort();
            log.info("\n" +
                     "**************************************************\n" +
                     "*****   SERVER IS RUNNING ON PORT {}   *****\n" +
                     "**************************************************", port);
        } else {
            log.warn("ApplicationContext não é WebServerApplicationContext - não foi possível obter a porta do servidor.");
        }
    }
}


