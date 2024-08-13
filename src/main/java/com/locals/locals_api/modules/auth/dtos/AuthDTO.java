package com.locals.locals_api.modules.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {

    private String email;
    private String password;
}
