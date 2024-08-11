package com.locals.locals_api.modules.users.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.services.CreateUserService;
import com.locals.locals_api.modules.users.services.ShowUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private ShowUserService showUserService;

    @PostMapping
    public ResponseEntity<UsersEntity> create(@Valid @RequestBody UsersEntity usersEntity) {

        UsersEntity createdUser = createUserService.execute(usersEntity);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable UUID id) {
        UsersEntity user = showUserService.execute(id);
        return ResponseEntity.ok(user);
    }
}

