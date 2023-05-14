package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.service.ImageStorageService;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("media")
public class MediaController {
    @Autowired
    private ImageStorageService service;
    
    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getMedia(@PathVariable String filename) throws IOException {
        Resource file = service.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
                
    }
            
}
