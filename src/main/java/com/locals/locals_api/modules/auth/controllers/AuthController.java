package com.locals.locals_api.modules.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locals.locals_api.modules.auth.dtos.AuthDTO;
import com.locals.locals_api.modules.auth.dtos.ResponseAuthDTO;
import com.locals.locals_api.modules.auth.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/auth")
@Tag(name="Auth", description="Essas são todas as rotas de autenticação")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping
    @Operation(summary="Autenticação de usuário", description="Essa rota permite ao usuário realizar sua autenticação")
    public ResponseEntity<ResponseAuthDTO> create (@RequestBody AuthDTO authDTO) {
        var output = this.authService.execute(authDTO);
        return ResponseEntity.ok().body(output);
    }

}
