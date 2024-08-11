package com.locals.locals_api.modules.users.services;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

public class ShowUserServiceTest {

    @InjectMocks
    private ShowUserService showUserService;

    @Mock
    private UsersRepository usersRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Criando entidade mock
        UUID userId = UUID.randomUUID();
        UsersEntity user = new UsersEntity();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setName("Test User");

        // resultado da consulta do mock
        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));

        // Executando o método de consulta
        UsersEntity result = showUserService.execute(userId);

        // Verificando se o resultado está correto
        assertEquals(userId, result.getId());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test User", result.getName());
        verify(usersRepository).findById(userId);
    }

    @Test
    void testExecute_UserNotFound() {
        // Criando entidade mock
        UUID userId = UUID.randomUUID();

        // Consulta do mock para não encontrar o usuário
        when(usersRepository.findById(userId)).thenReturn(Optional.empty());

        // Verificando se a exceção é lançada corretamente
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            showUserService.execute(userId);
        });

        assertEquals("User with ID " + userId + " does not exist.", thrown.getMessage());
        verify(usersRepository).findById(userId);
    }
}

