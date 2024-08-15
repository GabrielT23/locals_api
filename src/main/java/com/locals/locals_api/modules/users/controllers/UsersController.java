package com.locals.locals_api.modules.users.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locals.locals_api.modules.users.dtos.UpdateUserDTO;
import com.locals.locals_api.modules.users.entities.UsersEntity;
import com.locals.locals_api.modules.users.services.CreateUserService;
import com.locals.locals_api.modules.users.services.DeleteUserService;
import com.locals.locals_api.modules.users.services.ShowUserService;
import com.locals.locals_api.modules.users.services.UpdateUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Essas são todas as rotas de usuário")
public class UsersController {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private ShowUserService showUserService;

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private UpdateUserService updateUserService;

    @PostMapping
    @Operation(summary = "Criação de usuário", description = "Essa rota permite que criar um usuário")
    public ResponseEntity<UsersEntity> create(
            @Valid @RequestBody UsersEntity usersEntity) {
        UsersEntity createdUser = createUserService.execute(usersEntity);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    @SecurityRequirement(name="jwt_auth")
    @GetMapping("/{id}")
    @Operation(summary = "Busca de usuário", description = "Essa rota permite ao usuário autenticado buscar um usuário pelo id")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable UUID id) {
        UsersEntity user = showUserService.execute(id);
        return ResponseEntity.ok(user);
    }
    @SecurityRequirement(name="jwt_auth")
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de usuário", description = "Essa rota permite ao usuário autenticado deletar seu registro")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name="jwt_auth")
    @PutMapping("/{id}")
    @Operation(summary = "Atualização de usuário", description = "Essa rota permite ao usuário autenticado atualizar seu registro")
    public ResponseEntity<UpdateUserDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        UsersEntity updatedUser = updateUserService.execute(id, updateUserDTO);
        return ResponseEntity.ok(updateUserDTO);
    }
}

