package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.dto.SkillDTO;
import com.nicogmerz4.portfolio.service.CustomResponse;
import com.nicogmerz4.portfolio.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/api/skills")
@RestController
public class SkillController {
        
    @Autowired
    private SkillService service;
    
    @GetMapping
    public ResponseEntity getSkills() {
        CustomResponse response = service.getSkills();
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping
    public ResponseEntity createSkill(@RequestBody @Valid SkillDTO newSkill){
        CustomResponse response = service.createSkill(newSkill);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity findSkill(@PathVariable Long id, @RequestBody @Valid SkillDTO editedSkill) {
        CustomResponse response = service.editSkill(id, editedSkill);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        CustomResponse response = service.deleteSkill(id);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping("/{id}/icon")
    public ResponseEntity uploadExperienceLogo(@PathVariable Long id,  @RequestParam("icon") MultipartFile file) {
        CustomResponse response = service.uploadSkillLogo(id, file);
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
