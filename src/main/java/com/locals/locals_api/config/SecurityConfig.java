package com.locals.locals_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.locals.locals_api.middleware.SecurityFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    private static final String[] SWAGGER_WHITELIST = {
        // OpenAPI JSON/YAML
        "/v3/api-docs",
        "/v3/api-docs/**",
        // Swagger UI and assets
        "/swagger-ui.html",
        "/swagger-ui/index.html",
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/webjars/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // suas rotas públicas
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/locals").permitAll()
                .requestMatchers("/auth").permitAll()
                // Swagger sempre público
                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                // todo o resto autenticado
                .anyRequest().authenticated()
            )
            // adiciona seu filtro custom antes da autenticação básica
            .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
            ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




