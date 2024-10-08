package com.locals.locals_api.modules.users.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {

    @Schema(example = "joao")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Schema(example = "joao@gmail.com")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(example = "1234567")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}

