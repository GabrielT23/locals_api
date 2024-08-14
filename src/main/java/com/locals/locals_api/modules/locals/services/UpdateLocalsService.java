package com.locals.locals_api.modules.locals.services;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.locals.locals_api.modules.locals.dtos.UpdateLocalsDTO;
import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;
import com.locals.locals_api.utils.ImageUploadUtil;

@Service
public class UpdateLocalsService {

    @Autowired
    private LocalsRepository localsRepository;

    public LocalsEntity execute(UUID id, UpdateLocalsDTO updateLocalsDTO) {

        Optional<LocalsEntity> optionalLocalsEntity = localsRepository.findById(id);

        if (!optionalLocalsEntity.isPresent()) {
            throw new RuntimeException("LocalsEntity with ID " + id + " does not exist.");
        }

        LocalsEntity existingLocalsEntity = optionalLocalsEntity.get();

        if(!(existingLocalsEntity.getUser().getId().equals(updateLocalsDTO.getUserId()))){
            throw new RuntimeException("user not unauthorized");
        }

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

        localsRepository.save(existingLocalsEntity);

        return existingLocalsEntity;
    }

    public LocalsEntity execute(UUID id, UpdateLocalsDTO updateLocalsDTO, MultipartFile imageFile) throws IOException {

        Optional<LocalsEntity> optionalLocalsEntity = localsRepository.findById(id);
    
        if (!optionalLocalsEntity.isPresent()) {
            throw new RuntimeException("LocalsEntity with ID " + id + " does not exist.");
        }
    
        LocalsEntity existingLocalsEntity = optionalLocalsEntity.get();

        if(!(existingLocalsEntity.getUser().getId().equals(updateLocalsDTO.getUserId()))){
            throw new RuntimeException("user not unauthorized");
        }
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
    
        if (imageFile != null && !imageFile.isEmpty()) {
            // Verifica se a entidade já tem uma imagem associada
            if (existingLocalsEntity.getImageName() != null) {
                // Deleta a imagem anterior
                ImageUploadUtil.deleteImage(existingLocalsEntity.getImageName());
            }
    
            // Salvando a nova imagem
            String imageName = ImageUploadUtil.saveImage(imageFile);
            existingLocalsEntity.setImageName(imageName);
        }

        localsRepository.save(existingLocalsEntity);
        
        return existingLocalsEntity;
    }
}


