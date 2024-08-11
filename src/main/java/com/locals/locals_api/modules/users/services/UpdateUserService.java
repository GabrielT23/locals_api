package com.locals.locals_api.modules.users.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.users.dtos.UpdateUserDTO;
import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

@Service
public class UpdateUserService {

    @Autowired
    private UsersRepository usersRepository;

    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersEntity execute(UUID id, UpdateUserDTO updateUserDTO) {

        Optional<UsersEntity> optionalUser = usersRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User with ID " + id + " does not exist.");
        }

        UsersEntity existingUser = optionalUser.get();


        if (updateUserDTO.getName() != null) {
            existingUser.setName(updateUserDTO.getName());
        }
        if (updateUserDTO.getEmail() != null) {
            existingUser.setEmail(updateUserDTO.getEmail());
        }
        if (updateUserDTO.getPassword() != null) {
            String passwordEncode = passwordEncoder.encode((updateUserDTO.getPassword()));
            existingUser.setPassword(passwordEncode);
        }

        return usersRepository.save(existingUser);
    }
}

