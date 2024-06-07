package com.icodeap.ecommerce.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadFile {

    private final String FOLDER = "images";
    private final String IMG_DEFAULT = "default.jpg";

    public String upload(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            var bytes = multipartFile.getBytes();
            var path = Paths.get(FOLDER).resolve(multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            return multipartFile.getOriginalFilename();
        }
        return IMG_DEFAULT;
    }

    public void delete(String fileName) {
        Paths.get(FOLDER).resolve(fileName).toFile().delete();
    }
}
