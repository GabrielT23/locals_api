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


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping
    public ResponseEntity<ResponseAuthDTO> create (@RequestBody AuthDTO authDTO) {
        var output = this.authService.execute(authDTO);
        return ResponseEntity.ok().body(output);
    }

}
