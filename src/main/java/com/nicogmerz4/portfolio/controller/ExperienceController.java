package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.dto.ExperienceDTO;
import com.nicogmerz4.portfolio.model.Experience;
import com.nicogmerz4.portfolio.service.CustomResponse;
import com.nicogmerz4.portfolio.service.ExperienceService;
import com.nicogmerz4.portfolio.service.ImageStorageService;
import com.sun.net.httpserver.Headers;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {
        
    @Autowired
    private ExperienceService service;
    
    @GetMapping
    public ResponseEntity getExperiences() {
        HttpHeaders responseHeaders = new HttpHeaders();
 
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        CustomResponse response = service.getExperiences();
        
        return new ResponseEntity(response.getBody(), responseHeaders, response.getHttpStatus());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findExperience(@PathVariable Long id) {
        CustomResponse response = service.findExperience(id);
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping
    public ResponseEntity createExperience(@RequestBody @Valid ExperienceDTO newExperience) throws ParseException{
        CustomResponse response = service.createExperience(newExperience);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity findExperience(@PathVariable Long id, @RequestBody @Valid ExperienceDTO editedExperience) throws ParseException {
        CustomResponse response = service.editExperience(id, editedExperience);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteExperience(@PathVariable Long id) {
        CustomResponse response = service.deleteExperience(id);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping("/{id}/logo")
    public ResponseEntity uploadExperienceLogo(@PathVariable Long id,  @RequestParam("logo") MultipartFile file) {
        CustomResponse response = service.uploadExperienceLogo(id, file);
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
