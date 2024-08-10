package com.locals.locals_api.modules.locals.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;

@Service
public class ListLocalsService {
     @Autowired
    private LocalsRepository localsRepository;

    public List<LocalsEntity> execute() {
        return localsRepository.findAllByOrderByCreatedAtAsc();
    }
}
