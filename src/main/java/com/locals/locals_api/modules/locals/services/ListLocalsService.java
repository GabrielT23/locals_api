package com.locals.locals_api.modules.locals.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.locals.dtos.ResponseLocalsDTO;
import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;
import com.locals.locals_api.utils.ImageUploadUtil;

@Service
public class ListLocalsService {
    @Autowired
    private LocalsRepository localsRepository;

    public List<ResponseLocalsDTO> execute() {
        // Obtém a lista de entidades
        List<LocalsEntity> localsList = localsRepository.findAllByOrderByCreatedAtAsc();

        // Converte a lista de entidades para a lista de DTOs
        return localsList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ResponseLocalsDTO convertToDTO(LocalsEntity localsEntity) {
        ResponseLocalsDTO dto = new ResponseLocalsDTO();
        dto.setId(localsEntity.getId());
        dto.setName(localsEntity.getName());
        dto.setNeighborhood(localsEntity.getNeighborhood());
        dto.setCity(localsEntity.getCity());
        dto.setState(localsEntity.getState());

        if (localsEntity.getImageName() != null) {
            String imageURL = ImageUploadUtil.getImageURL(localsEntity.getImageName());
            dto.setImageURL(imageURL);
        }

        return dto;
    }
}
