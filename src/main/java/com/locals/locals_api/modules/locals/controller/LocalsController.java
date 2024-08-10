package com.locals.locals_api.modules.locals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.services.CreateLocalsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/locals")
public class LocalsController {

    @Autowired
    private CreateLocalsService createLocalsService;

    @PostMapping()
    public LocalsEntity post(@Valid @RequestBody LocalsEntity localsEntity ) {
        return this.createLocalsService.execute(localsEntity);
    }
}
