package com.locals.locals_api.modules.locals.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.locals.locals_api.modules.locals.dtos.ResponseLocalsDTO;
import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;

public class ListLocalsServiceTest {

    @InjectMocks
    private ListLocalsService listLocalsService;

    @Mock
    private LocalsRepository localsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {

        LocalsEntity entity1 = new LocalsEntity();
        LocalsEntity entity2 = new LocalsEntity();
        entity1.setCreatedAt(LocalDateTime.of(2024, 4, 12, 7, 0));
        entity2.setCreatedAt(LocalDateTime.of(2024, 1, 1, 12, 0));
        List<LocalsEntity> expectedEntities = Arrays.asList(entity1, entity2);

        // Configura o mock para retornar a lista esperada
        when(localsRepository.findAllByOrderByCreatedAtAsc()).thenReturn(expectedEntities);

        // Executa o método a ser testado
        List<ResponseLocalsDTO> actualEntities = listLocalsService.execute();

        // Verifica o resultado
        assertNotNull(actualEntities);
        assertEquals(expectedEntities.size(), actualEntities.size());
        assertEquals(expectedEntities.get(0).getId(), actualEntities.get(0).getId());
        assertEquals(expectedEntities.get(1).getId(), actualEntities.get(1).getId());

        // Verifica se o método do repositório foi chamado
        verify(localsRepository, times(1)).findAllByOrderByCreatedAtAsc();
    }
}
