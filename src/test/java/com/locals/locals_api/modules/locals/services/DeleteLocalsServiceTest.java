package com.locals.locals_api.modules.locals.services;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;
import com.locals.locals_api.modules.users.entities.UsersEntity;

public class DeleteLocalsServiceTest {

    @Mock
    private LocalsRepository localsRepository;

    @InjectMocks
    private DeleteLocalsService deleteLocalsService;

    private UUID existingId;
    private UUID nonExistingId;
    private LocalsEntity existingLocalsEntity;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
        existingLocalsEntity = new LocalsEntity();
        existingLocalsEntity.setId(existingId);
        existingLocalsEntity.setImageName(null); // Ou configure um nome de imagem, se necessário
        userId = UUID.randomUUID();
        UsersEntity existingUser = new UsersEntity();
        existingUser.setId(userId);
        existingLocalsEntity.setUser(existingUser);

        // Configura o mock para simular comportamento esperado
        when(localsRepository.existsById(existingId)).thenReturn(true);
        when(localsRepository.existsById(nonExistingId)).thenReturn(false);
        when(localsRepository.findById(existingId)).thenReturn(Optional.of(existingLocalsEntity));
        when(localsRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        
        doNothing().when(localsRepository).deleteById(existingId);
    }

    @Test
    void testExecuteWithExistingId() {
        // Testando o método com uma entidade existente
        deleteLocalsService.execute(existingId, userId);
        verify(localsRepository, times(1)).deleteById(existingId);
    }

    @Test
    void testExecuteWithNonExistingId() {
        // Verificando se uma exceção é lançada quando a entidade não existe
        Exception exception = assertThrows(RuntimeException.class, () -> {
            deleteLocalsService.execute(nonExistingId, userId);
        });

        String expectedMessage = "LocalsEntity with ID " + nonExistingId + " does not exist.";
        String actualMessage = exception.getMessage();

        // Verificando se a mensagem da exceção está correta
        assertTrue(actualMessage.contains(expectedMessage));

        // Verificando se o método deleteById não foi chamado
        verify(localsRepository, never()).deleteById(nonExistingId);
    }
}




