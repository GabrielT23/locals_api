package com.locals.locals_api.modules.locals.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateLocalsDTO {

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    private String neighborhood;

    private String city;
    
    private String state;
}

