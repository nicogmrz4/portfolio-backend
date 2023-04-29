package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.model.Skill;
import com.nicogmerz4.portfolio.service.CustomResponse;
import com.nicogmerz4.portfolio.service.SkillService;
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
    public ResponseEntity createSkill(@RequestBody Skill newSkill){
        CustomResponse response = service.createSkill(newSkill);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity findSkill(@PathVariable Long id, @RequestBody Skill editedSkill) {
        CustomResponse response = service.editSkill(id, editedSkill);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSkill(@PathVariable Long id) {
        CustomResponse response = service.deleteSkill(id);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
