package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.model.Skill;
import com.nicogmerz4.portfolio.repository.SkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    @Autowired
    private SkillRepository repo;
    
    public CustomResponse getSkills() {
        List<Skill> projects = repo.findAll();
        CustomResponse response = new CustomResponse();
        response.getBody().setData(projects);
        return response;
    }
    
    public CustomResponse createSkill(Skill newSkill) {
        Skill project = repo.save(newSkill);
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Skill created");
        response.getBody().addData(project);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteSkill(Long id) {
        Skill project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            repo.delete(project);
            response.getBody().setMessage("Skill deleted");
            return response;
        }
        
        response.getBody().setMessage("Skill does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse editSkill(Long id, Skill editedSkill) {
        Skill project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            project = editedSkill;
            project.setId(id);
            repo.save(project);
            response.getBody().addData(project);
            response.getBody().setMessage("Skill edited");
            return response;
        }
        
        response.getBody().setMessage("Skill does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
}
