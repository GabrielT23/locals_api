package com.locals.locals_api.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadUtil {

    private static final String UPLOAD_DIR = "uploads/";
    private static final String IMAGE_BASE_URL = "http://localhost:8080/uploads/";

    public static String getImageURL(String imageName) {
        return IMAGE_BASE_URL + imageName;
    }

    public static String saveImage(MultipartFile imageFile) throws IOException {
        String originalFileName = imageFile.getOriginalFilename();
        String fileExtension = getFileExtension(originalFileName);
        String newFileName = UUID.randomUUID().toString() + fileExtension;
        Files.createDirectories(Paths.get(UPLOAD_DIR));
        Files.copy(imageFile.getInputStream(), Paths.get(UPLOAD_DIR + newFileName));
        return newFileName;
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static void deleteImage(String imageName) {
        File imageFile = new File("uploads/" + imageName);
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }
}

