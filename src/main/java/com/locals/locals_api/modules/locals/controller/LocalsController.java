package com.locals.locals_api.modules.locals.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.locals.locals_api.modules.locals.dtos.ResponseLocalsDTO;
import com.locals.locals_api.modules.locals.dtos.UpdateLocalsDTO;
import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.services.CreateLocalsService;
import com.locals.locals_api.modules.locals.services.DeleteLocalsService;
import com.locals.locals_api.modules.locals.services.ListLocalsService;
import com.locals.locals_api.modules.locals.services.UpdateLocalsService;
import com.locals.locals_api.modules.users.entities.UsersEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/locals")
@Tag(name="Locals", description="Essas são todas as rotas de locais")
public class LocalsController {

    @Autowired
    private CreateLocalsService createLocalsService;

    @Autowired
    private ListLocalsService listLocalsService;

    @Autowired
    private UpdateLocalsService updateLocalsService;

    @Autowired
    private DeleteLocalsService deleteLocalsService;

    @SecurityRequirement(name = "jwt_auth")
    @PostMapping()
    @Operation(summary = "Criação de local", description = "Essa rota permite ao usuário autenticado criar um local")
    public LocalsEntity post(
        @ModelAttribute LocalsEntity localsEntity, HttpServletRequest request,
        @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        var user_id = request.getAttribute("user_id");
        UsersEntity userEntity = new UsersEntity();
        userEntity.setId(UUID.fromString(user_id.toString()));
        localsEntity.setUser(userEntity);
        if (imageFile != null && !imageFile.isEmpty()) {
            return this.createLocalsService.execute(localsEntity, imageFile);
        } else {
            return this.createLocalsService.execute(localsEntity);
        }
    }

    
    @GetMapping()
    @Operation(summary="Listagem de locais", description="Essa rota lista de maneira ordenada pela data todos os locais")
    public ResponseEntity<List<ResponseLocalsDTO>> list() {
        List<ResponseLocalsDTO> localsOrd = this.listLocalsService.execute();
        return ResponseEntity.status(HttpStatus.CREATED).body(localsOrd);
    }

    @SecurityRequirement(name = "jwt_auth")
    @PutMapping("/{id}")
    @Operation(summary="Atualização de local", description="Essa rota permite ao usuário autenticado atualizar o local")
    public ResponseEntity update(@Valid @ModelAttribute UpdateLocalsDTO updateLocalsEntity,  
    @PathVariable UUID id, HttpServletRequest request,
    @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        var user_id = request.getAttribute("user_id");
        updateLocalsEntity.setUserId(UUID.fromString(user_id.toString()));

        if (imageFile != null && !imageFile.isEmpty()) {
            this.updateLocalsService.execute(id, updateLocalsEntity, imageFile);
            return ResponseEntity.ok(updateLocalsEntity);
        }
        else{
            this.updateLocalsService.execute(id, updateLocalsEntity);
            return ResponseEntity.ok(updateLocalsEntity);
        }
    }

    @SecurityRequirement(name = "jwt_auth")
    @DeleteMapping("/{id}")
    @Operation(summary="Exclusão de local", description="Essa rota permite ao usuário autenticado excluir o local")
    public void delete(@PathVariable UUID id, HttpServletRequest request) {
        var user_id = request.getAttribute("user_id");
        this.deleteLocalsService.execute(id, UUID.fromString(user_id.toString()));
    }
}
