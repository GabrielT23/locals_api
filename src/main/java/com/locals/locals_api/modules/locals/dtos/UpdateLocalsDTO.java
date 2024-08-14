package com.locals.locals_api.modules.locals.dtos;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateLocalsDTO {

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @Schema(example = "Estado A")
    private String neighborhood;

    @Schema(example = "Estado A")
    private String city;
        
    @Schema(example = "Estado A")
    private String state;

    @Schema(hidden = true)
    private UUID userId;
}

