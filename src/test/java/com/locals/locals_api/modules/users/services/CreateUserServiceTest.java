package com.locals.locals_api.modules.users.services;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

public class CreateUserServiceTest {

    @InjectMocks
    private CreateUserService createUserService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Criando entidade mock
        UsersEntity user = new UsersEntity();
        user.setEmail("test@example.com");
        String passwordEncode = passwordEncoder.encode("password123");
        user.setPassword("password123");

        // resultado da consulta mock
        when(usersRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(usersRepository.save(user)).thenReturn(user);

        // Verificando service create
        UsersEntity result = createUserService.execute(user);
        verify(usersRepository).findByEmail(user.getEmail());
        verify(usersRepository).save(user);
        assertEquals(passwordEncode, result.getPassword());
    }

    @Test
    void testExecute_EmailAlreadyExists() {
        // criando entidade mock
        UsersEntity user = new UsersEntity();
        user.setEmail("test@example.com");

        // resultado da consulta mock
        when(usersRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // verificação do método create para caso de falha
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            createUserService.execute(user);
        });

        assertEquals("Email already exists.", thrown.getMessage());
        verify(usersRepository).findByEmail(user.getEmail());
        verify(usersRepository, never()).save(user);
        verify(passwordEncoder, never()).encode(anyString());
    }
}

