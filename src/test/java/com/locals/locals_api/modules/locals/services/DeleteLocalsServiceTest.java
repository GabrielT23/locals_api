package com.locals.locals_api.modules.locals.services;

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

import com.locals.locals_api.modules.locals.repositories.LocalsRepository;

public class DeleteLocalsServiceTest {

    @Mock
    private LocalsRepository localsRepository;

    @InjectMocks
    private DeleteLocalsService deleteLocalsService;

    private UUID existingId;
    private UUID nonExistingId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();

        // Configura o mock para simular comportamento esperado
        when(localsRepository.existsById(existingId)).thenReturn(true);
        when(localsRepository.existsById(nonExistingId)).thenReturn(false);
        doNothing().when(localsRepository).deleteById(existingId);
    }

    @Test
    void testExecuteWithExistingId() {
        // Testa o método com uma entidade existente
        deleteLocalsService.execute(existingId);

        // Verificando se o método deleteById foi chamado exatamente uma vez com o ID correto
        verify(localsRepository, times(1)).deleteById(existingId);
    }

    @Test
    void testExecuteWithNonExistingId() {
        // Verificando se uma exceção é lançada quando a entidade não existe
        Exception exception = assertThrows(RuntimeException.class, () -> {
            deleteLocalsService.execute(nonExistingId);
        });

        String expectedMessage = "LocalsEntity with ID " + nonExistingId + " does not exist.";
        String actualMessage = exception.getMessage();

        // Verificando se a mensagem da exceção está correta
        assertTrue(actualMessage.contains(expectedMessage));

        // Verificando se o método deleteById não foi chamado
        verify(localsRepository, never()).deleteById(nonExistingId);
    }
}



