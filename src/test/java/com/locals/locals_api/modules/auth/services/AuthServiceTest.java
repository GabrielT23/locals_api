package com.locals.locals_api.modules.auth.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.locals.locals_api.modules.auth.dtos.AuthDTO;
import com.locals.locals_api.modules.auth.dtos.ResponseAuthDTO;
import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey = "locals-secret";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        AuthDTO authDTO = new AuthDTO("test@example.com", "password123");


        UsersEntity user = new UsersEntity();
        UUID id = UUID.randomUUID();
        user.setId(id);
        user.setPassword("encodedPassword");

        when(usersRepository.findByEmail(authDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(authDTO.getPassword(), user.getPassword())).thenReturn(true);

        ResponseAuthDTO response = authService.execute(authDTO);


        assertNotNull(response.getToken());
        assertTrue(response.getToken().startsWith("eyJ"));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String expectedToken = JWT.create()
                .withExpiresAt(Instant.now().plus(Duration.ofDays(1)))
                .withIssuer("locals")
                .withSubject(user.getId().toString())
                .sign(algorithm);
        assertEquals(expectedToken, response.getToken());
    }

    @Test
    void testExecute_Failure_InvalidEmail() {
        // Arrange
        AuthDTO authDTO = new AuthDTO("invalid@example.com", "password123");
        authDTO.setEmail("invalid@example.com");
        authDTO.setPassword("password123");

        when(usersRepository.findByEmail(authDTO.getEmail())).thenReturn(Optional.empty());


        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            authService.execute(authDTO);
        });
        assertEquals("Email or password invalid", thrown.getMessage());
    }

    @Test
    void testExecute_Failure_InvalidPassword() {

        AuthDTO authDTO = new AuthDTO("test@example.com", "wrongPassword");

        UUID id = UUID.randomUUID();
        UsersEntity user = new UsersEntity();
        user.setId(id);
        user.setPassword("encodedPassword");

        when(usersRepository.findByEmail(authDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(authDTO.getPassword(), user.getPassword())).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            authService.execute(authDTO);
        });
        assertEquals("Email or password invalid", thrown.getMessage());
    }
}

