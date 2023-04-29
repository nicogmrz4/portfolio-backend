package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.model.Academy;
import com.nicogmerz4.portfolio.repository.AcademyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AcademyService {
    @Autowired
    AcademyRepository repo;
    
    public CustomResponse getAcademies() {
        List<Academy> projects = repo.findAll();
        CustomResponse response = new CustomResponse();
        response.getBody().setData(projects);
        return response;
    }
    
    public CustomResponse findAcademy(Long id) {
        Academy project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            response.getBody().addData(project);
            return response;
        }
        
        response.getBody().setMessage("Academy not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse createAcademy(Academy newAcademy) {
        Academy project = repo.save(newAcademy);
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Academy created");
        response.getBody().addData(project);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteAcademy(Long id) {
        Academy project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            repo.delete(project);
            response.getBody().setMessage("Academy deleted");
            return response;
        }
        
        response.getBody().setMessage("Academy does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse editAcademy(Long id, Academy editedAcademy) {
        Academy project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            project = editedAcademy;
            project.setId(id);
            repo.save(project);
            response.getBody().addData(project);
            response.getBody().setMessage("Academy edited");
            return response;
        }
        
        response.getBody().setMessage("Academy does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
}
