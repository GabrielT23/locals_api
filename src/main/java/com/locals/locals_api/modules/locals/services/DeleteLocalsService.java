package com.locals.locals_api.modules.locals.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.locals.repositories.LocalsRepository;

@Service
public class DeleteLocalsService {

    @Autowired
    private LocalsRepository localsRepository;

    public void execute(UUID id) {
        // Verifica se o registro existe
        if (!localsRepository.existsById(id)) {
            throw new RuntimeException("LocalsEntity with ID " + id + " does not exist.");
        }
        
        localsRepository.deleteById(id);
    }
}


