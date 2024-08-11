package com.locals.locals_api.modules.locals.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.locals.locals_api.modules.locals.dtos.ResponseLocalsDTO;
import com.locals.locals_api.modules.locals.dtos.UpdateLocalsDTO;
import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.services.CreateLocalsService;
import com.locals.locals_api.modules.locals.services.DeleteLocalsService;
import com.locals.locals_api.modules.locals.services.ListLocalsService;
import com.locals.locals_api.modules.locals.services.UpdateLocalsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/locals")
public class LocalsController {

    @Autowired
    private CreateLocalsService createLocalsService;

    @Autowired
    private ListLocalsService listLocalsService;

    @Autowired
    private UpdateLocalsService updateLocalsService;

    @Autowired
    private DeleteLocalsService deleteLocalsService;

    @PostMapping()
    public LocalsEntity post(
        @ModelAttribute LocalsEntity localsEntity,
        @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
    
        if (imageFile != null && !imageFile.isEmpty()) {
            return this.createLocalsService.execute(localsEntity, imageFile);
        } else {
            return this.createLocalsService.execute(localsEntity);
        }
    }

    @GetMapping()
    public ResponseEntity<List<ResponseLocalsDTO>> list() {
        List<ResponseLocalsDTO> localsOrd = this.listLocalsService.execute();
        return ResponseEntity.status(HttpStatus.CREATED).body(localsOrd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalsEntity> update(@Valid @ModelAttribute UpdateLocalsDTO updateLocalsEntity,  
    @PathVariable UUID id,
    @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            LocalsEntity updatedLocalsEntity = this.updateLocalsService.execute(id, updateLocalsEntity, imageFile);
            return ResponseEntity.ok(updatedLocalsEntity);
        }
        else{
            LocalsEntity updatedLocalsEntity = this.updateLocalsService.execute(id, updateLocalsEntity);
            return ResponseEntity.ok(updatedLocalsEntity);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.deleteLocalsService.execute(id);
    }
}
