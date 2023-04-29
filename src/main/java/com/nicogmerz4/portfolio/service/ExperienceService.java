package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nicogmerz4.portfolio.model.Experience;
import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class ExperienceService {
    @Autowired
    ExperienceRepository repo;
    
    public CustomResponse getExperiences() {
        List<Experience> projects = repo.findAll();
        CustomResponse response = new CustomResponse();
        response.getBody().setData(projects);
        return response;
    }
    
    public CustomResponse findExperience(Long id) {
        Experience project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            response.getBody().addData(project);
            return response;
        }
        
        response.getBody().setMessage("Experience not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse createExperience(Experience newExperience) {
        Experience project = repo.save(newExperience);
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Experience created");
        response.getBody().addData(project);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteExperience(Long id) {
        Experience project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            repo.delete(project);
            response.getBody().setMessage("Experience deleted");
            return response;
        }
        
        response.getBody().setMessage("Experience does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse editExperience(Long id, Experience editedExperience) {
        Experience project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            project = editedExperience;
            project.setId(id);
            repo.save(project);
            response.getBody().addData(project);
            response.getBody().setMessage("Experience edited");
            return response;
        }
        
        response.getBody().setMessage("Experience does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
}
