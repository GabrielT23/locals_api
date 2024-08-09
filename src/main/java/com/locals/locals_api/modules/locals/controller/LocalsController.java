package com.locals.locals_api.modules.locals.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;

@RestController
@RequestMapping("/locals")
public class LocalsController {
    
    @PostMapping()
    public void post( @RequestBody LocalsEntity localsEntity ) {
        System.out.println(localsEntity.getCity());
    }
}
