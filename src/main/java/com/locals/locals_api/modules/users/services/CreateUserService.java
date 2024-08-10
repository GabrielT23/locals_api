package com.locals.locals_api.modules.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.repositories.UsersRepository;

@Service
public class CreateUserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersEntity execute(UsersEntity usersEntity) {
        // Verifica se já existe um usuário com o mesmo email
        if (usersRepository.findByEmail(usersEntity.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        // Criptografia da senha do usuáro
        String password = passwordEncoder.encode((usersEntity.getPassword()));
        usersEntity.setPassword(password);
        
        // Salva o novo usuário no banco de dados
        return usersRepository.save(usersEntity);
    }
}

