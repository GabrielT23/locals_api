package com.locals.locals_api.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.services.CreateUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private CreateUserService createUserService;

    @PostMapping
    public ResponseEntity<UsersEntity> create(@Valid @RequestBody UsersEntity usersEntity) {

        UsersEntity createdUser = createUserService.execute(usersEntity);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}

