package com.locals.locals_api.modules.locals.entities;

import java.util.UUID;

import lombok.Data;

@Data()
public class LocalsEntity {
    
    private UUID id;
    private String name;
    private String neighborhood;
    private String city;
    private String state;
}
