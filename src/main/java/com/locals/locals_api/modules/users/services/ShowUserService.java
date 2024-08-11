package com.locals.locals_api.modules.users.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

@Service
public class ShowUserService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity execute(UUID id) {
        Optional<UsersEntity> optionalUser = usersRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User with ID " + id + " does not exist.");
        }

        return optionalUser.get();
    }
}

