package com.locals.locals_api.modules.locals.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.locals.locals_api.modules.locals.entities.LocalsEntity;
import com.locals.locals_api.modules.locals.repositories.LocalsRepository;
import com.locals.locals_api.utils.ImageUploadUtil;

@Service
public class CreateLocalsService {
    @Autowired
    private LocalsRepository localsRepository;

    public LocalsEntity execute(LocalsEntity localsEntity, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageName = ImageUploadUtil.saveImage(imageFile);
            localsEntity.setImageName(imageName);
        }
        return localsRepository.save(localsEntity);
    }

    public LocalsEntity execute(LocalsEntity localsEntity) throws IOException {
        System.out.println(localsEntity);
        return localsRepository.save(localsEntity);
    }
}
