package com.nicogmerz4.portfolio.service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService {

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    public void delete(String filename) {
        try {
            Path pathToFile = rootLocation.resolve(filename);
            Files.delete(pathToFile);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo eliminar la imagen.");
        }
    }

    public String store(MultipartFile file) {
        try {
            String fileType = getFileExtension(file);
            String filename = generateUniqueFileName("img-", fileType);
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la imagen", e);
        }
    }

    private String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Optional<String> fileExtension = getExtensionByStringHandling(fileName);
        
        if (!fileExtension.isEmpty()) {
            return fileExtension.get();
        }
        
        throw new RuntimeException("El archivo no posee una extensión");
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    public String generateUniqueFileName(String prefix, String suffix) {
        return (prefix != null ? prefix : "") + System.nanoTime()
                + (suffix != null ? suffix : "");
    }

    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".")));
    }
}
