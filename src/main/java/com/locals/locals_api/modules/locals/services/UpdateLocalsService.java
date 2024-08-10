package com.locals.locals_api.modules.locals.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.locals.dtos.UpdateLocalsDTO;
import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;

@Service
public class UpdateLocalsService {

    @Autowired
    private LocalsRepository localsRepository;

    public LocalsEntity execute(UUID id, UpdateLocalsDTO updateLocalsDTO) {
        // Verifica se a entidade com o ID especificado existe
        Optional<LocalsEntity> optionalLocalsEntity = localsRepository.findById(id);

        if (!optionalLocalsEntity.isPresent()) {
            throw new RuntimeException("LocalsEntity with ID " + id + " does not exist.");
        }

        LocalsEntity existingLocalsEntity = optionalLocalsEntity.get();

        // Atualiza os campos da entidade existente com os novos valores, apenas se eles estiverem presentes
        if (updateLocalsDTO.getName() != null) {
            existingLocalsEntity.setName(updateLocalsDTO.getName());
        }
        if (updateLocalsDTO.getNeighborhood() != null) {
            existingLocalsEntity.setNeighborhood(updateLocalsDTO.getNeighborhood());
        }
        if (updateLocalsDTO.getCity() != null) {
            existingLocalsEntity.setCity(updateLocalsDTO.getCity());
        }
        if (updateLocalsDTO.getState() != null) {
            existingLocalsEntity.setState(updateLocalsDTO.getState());
        }

        // Salva a entidade atualizada no banco de dados
        return localsRepository.save(existingLocalsEntity);
    }
}


