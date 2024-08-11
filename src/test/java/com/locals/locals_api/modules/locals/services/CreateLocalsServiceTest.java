package com.locals.locals_api.modules.locals.services;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;

class CreateLocalsServiceTest {

    @Mock
    private LocalsRepository localsRepository;

    @InjectMocks
    private CreateLocalsService createLocalsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() throws IOException {
        UUID id = UUID.randomUUID();
        LocalsEntity localsEntity = new LocalsEntity();
        localsEntity.setId(id);
        localsEntity.setName("Test Local");

        when(localsRepository.save(any(LocalsEntity.class))).thenReturn(localsEntity);
        LocalsEntity result = createLocalsService.execute(localsEntity);

        assertEquals(id, result.getId());
        assertEquals("Test Local", result.getName());
    }
}


