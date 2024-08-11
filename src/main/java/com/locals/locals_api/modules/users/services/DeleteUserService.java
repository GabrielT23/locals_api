package com.locals.locals_api.modules.users.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.users.repositories.UsersRepository;

@Service
public class DeleteUserService {

    @Autowired
    private UsersRepository usersRepository;

    public void execute(UUID id) {

        boolean exists = usersRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException("User with ID " + id + " does not exist.");
        }


        usersRepository.deleteById(id);
    }
}

