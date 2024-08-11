package com.locals.locals_api.modules.users.services;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.locals.locals_api.modules.users.dtos.UpdateUserDTO;
import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

public class UpdateUserServiceTest {

    @InjectMocks
    private UpdateUserService updateUserService;

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
        UUID userId = UUID.randomUUID();
        UsersEntity existingUser = new UsersEntity();
        existingUser.setId(userId);
        existingUser.setName("Old Name");
        existingUser.setEmail("old@example.com");
        existingUser.setPassword("oldpassword");
        String passwordEncode = passwordEncoder.encode("newpassword");
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName("New Name");
        updateUserDTO.setEmail("new@example.com");
        updateUserDTO.setPassword(passwordEncode);

        // resultado da consulta mock
        when(usersRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(usersRepository.save(existingUser)).thenReturn(existingUser);

        // Execução do método de atualização
        UsersEntity updatedUser = updateUserService.execute(userId, updateUserDTO);

        // Verificando resultado
        assertEquals("New Name", updatedUser.getName());
        assertEquals("new@example.com", updatedUser.getEmail());

        verify(usersRepository).findById(userId);
        verify(usersRepository).save(existingUser);
    }

    @Test
    void testExecute_UserNotFound() {

        UUID userId = UUID.randomUUID();
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName("New Name");

        // resultado da consulta mock para not found
        when(usersRepository.findById(userId)).thenReturn(Optional.empty());

        // Verificando se é lançada uma exceção
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            updateUserService.execute(userId, updateUserDTO);
        });

        assertEquals("User with ID " + userId + " does not exist.", thrown.getMessage());
        verify(usersRepository).findById(userId);
        verify(usersRepository, never()).save(any(UsersEntity.class));
    }
}

