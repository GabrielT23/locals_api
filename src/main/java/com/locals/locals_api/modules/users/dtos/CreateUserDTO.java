package com.locals.locals_api.modules.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {
    
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
