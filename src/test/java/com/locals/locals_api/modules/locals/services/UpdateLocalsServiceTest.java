package com.locals.locals_api.modules.locals.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.locals.locals_api.modules.locals.dtos.UpdateLocalsDTO;
import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;
import com.locals.locals_api.modules.users.entities.UsersEntity;

@SpringBootTest
public class UpdateLocalsServiceTest {

    @Mock
    private LocalsRepository localsRepository;

    @InjectMocks
    private UpdateLocalsService updateLocalsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_Success() {
        UUID id = UUID.randomUUID();
        LocalsEntity existingLocalsEntity = new LocalsEntity();
        existingLocalsEntity.setId(id);
        existingLocalsEntity.setName("Old Name");
        existingLocalsEntity.setNeighborhood("Old Neighborhood");
        existingLocalsEntity.setCity("Old City");
        existingLocalsEntity.setState("Old State");
        existingLocalsEntity.setCreatedAt(LocalDateTime.now());
        existingLocalsEntity.setUpdatedAt(LocalDateTime.now());

        UUID userId = UUID.randomUUID();
        UsersEntity existingUser = new UsersEntity();
        existingUser.setId(userId);
        existingLocalsEntity.setUser(existingUser);
        when(localsRepository.findById(id)).thenReturn(Optional.of(existingLocalsEntity));
        when(localsRepository.save(any(LocalsEntity.class))).thenReturn(existingLocalsEntity);
        

        UpdateLocalsDTO updateLocalsDTO = new UpdateLocalsDTO();
        updateLocalsDTO.setName("New Name");
        updateLocalsDTO.setNeighborhood("New Neighborhood");
        updateLocalsDTO.setUserId(userId);

        LocalsEntity updatedLocalsEntity = updateLocalsService.execute(id, updateLocalsDTO);

        assertEquals("New Name", updatedLocalsEntity.getName());
        assertEquals("New Neighborhood", updatedLocalsEntity.getNeighborhood());
        assertEquals("Old City", updatedLocalsEntity.getCity());
        assertEquals("Old State", updatedLocalsEntity.getState());
        verify(localsRepository, times(1)).findById(eq(id));
        verify(localsRepository, times(1)).save(any(LocalsEntity.class));
    }

    @Test
    public void testExecute_Failure() {
        UUID id = UUID.randomUUID();
        when(localsRepository.findById(id)).thenReturn(Optional.empty());

        UpdateLocalsDTO updateLocalsDTO = new UpdateLocalsDTO();
        updateLocalsDTO.setName("New Name");

        try {
            updateLocalsService.execute(id, updateLocalsDTO);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("LocalsEntity with ID " + id + " does not exist.", e.getMessage());
        }

        verify(localsRepository, times(1)).findById(eq(id));
        verify(localsRepository, never()).save(any(LocalsEntity.class));
    }
}


