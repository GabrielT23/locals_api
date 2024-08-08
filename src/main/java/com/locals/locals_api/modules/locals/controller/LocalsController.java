package com.locals.locals_api.modules.locals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locals")
public class LocalsController {
    
    @GetMapping("/")
    public String list() {
        return "Hello World";
    }
}
