package com.locals.locals_api.modules.locals.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;
import com.locals.locals_api.utils.ImageUploadUtil;

@Service
public class DeleteLocalsService {

    @Autowired
    private LocalsRepository localsRepository;

    public void execute(UUID id, UUID userId) {

        if (!localsRepository.existsById(id)) {
            throw new RuntimeException("LocalsEntity with ID " + id + " does not exist.");
        }
        Optional<LocalsEntity> existingLocalsEntity = localsRepository.findById(id);
        System.out.println(userId);
        if(!(existingLocalsEntity.get().getUser().getId().equals(userId))){
            throw new RuntimeException("user not unauthorized");
        }
        if (existingLocalsEntity.get().getImageName() != null) {
                // Deleta a imagem anterior
                ImageUploadUtil.deleteImage(existingLocalsEntity.get().getImageName());
            }
        
        localsRepository.deleteById(id);
    }
}


