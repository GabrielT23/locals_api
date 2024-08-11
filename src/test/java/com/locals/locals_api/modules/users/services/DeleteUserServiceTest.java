package com.locals.locals_api.modules.users.services;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.locals.locals_api.modules.users.repositories.UsersRepository;

public class DeleteUserServiceTest {

    @InjectMocks
    private DeleteUserService deleteUserService;

    @Mock
    private UsersRepository usersRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Criação de entidade mock
        UUID userId = UUID.randomUUID();

        // resultado da consulta mock para deletar
        when(usersRepository.existsById(userId)).thenReturn(true);

        // Execução do método
        deleteUserService.execute(userId);

        // Verificação do resultado
        verify(usersRepository).existsById(userId);
        verify(usersRepository).deleteById(userId);
    }

    @Test
    void testExecute_UserNotFound() {
        // Criação da entidade mock
        UUID userId = UUID.randomUUID();

        // Resultado da consulta mock para não existente
        when(usersRepository.existsById(userId)).thenReturn(false);

        // verificação do método
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            deleteUserService.execute(userId);
        });

        assertEquals("User with ID " + userId + " does not exist.", thrown.getMessage());
        verify(usersRepository).existsById(userId);
        verify(usersRepository, never()).deleteById(userId);
    }
}

