package com.locals.locals_api.modules.locals.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;

@Service
public class CreateLocalsService {
    @Autowired
    private LocalsRepository localsRepository;

    public LocalsEntity execute(LocalsEntity localsEntity) {

        LocalsEntity newLocalsEntity = this.localsRepository.save(localsEntity);

        return newLocalsEntity;
    }
}
