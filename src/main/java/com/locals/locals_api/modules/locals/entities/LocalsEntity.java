package com.locals.locals_api.modules.locals.entities;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LocalsEntity {

    private UUID id;
    
    @NotNull(message = "Name is not null")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @NotNull(message = "neighborhood is not null")
    @NotBlank(message = "Neighborhood is mandatory")
    private String neighborhood;
    
    @NotNull(message = "city is not null")
    @NotBlank(message = "City is mandatory")
    private String city;
    
    @NotNull(message = "state is not null")
    @NotBlank(message = "State is mandatory")
    private String state;
    
    private String createdAt;
    
    private String updatedAt;
}

