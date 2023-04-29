package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.model.Academy;
import com.nicogmerz4.portfolio.service.AcademyService;
import com.nicogmerz4.portfolio.service.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/academies")
@RestController
public class AcademyController {
        
    @Autowired
    private AcademyService service;
    
    @GetMapping
    public ResponseEntity getAcademys() {
        CustomResponse response = service.getAcademies();
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findAcademy(@PathVariable Long id) {
        CustomResponse response = service.findAcademy(id);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping
    public ResponseEntity createAcademy(@RequestBody Academy newAcademy){
        CustomResponse response = service.createAcademy(newAcademy);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity findAcademy(@PathVariable Long id, @RequestBody Academy editedAcademy) {
        CustomResponse response = service.editAcademy(id, editedAcademy);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAcademy(@PathVariable Long id) {
        CustomResponse response = service.deleteAcademy(id);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
