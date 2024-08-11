package com.locals.locals_api.modules.locals.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ResponseLocalsDTO {

    private UUID id;
    private String name;
    private String neighborhood;
    private String city;
    private String state;
    private String imageURL;

}