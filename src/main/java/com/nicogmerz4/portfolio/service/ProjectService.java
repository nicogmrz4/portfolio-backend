package com.nicogmerz4.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nicogmerz4.portfolio.repository.ProjectRepository;
import com.nicogmerz4.portfolio.model.Project;
import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repo;
    
    public CustomResponse getProjects() {
        List<Project> projects = repo.findAll();
        CustomResponse response = new CustomResponse();
        response.getBody().setData(projects);
        return response;
    }
    
    public CustomResponse findProject(Long id) {
        Project project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            response.getBody().addData(project);
            return response;
        }
        
        response.getBody().setMessage("Project not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse createProject(Project newProject) {
        Project project = repo.save(newProject);
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Project created");
        response.getBody().addData(project);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteProject(Long id) {
        Project project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            repo.delete(project);
            response.getBody().setMessage("Project deleted");
            return response;
        }
        
        response.getBody().setMessage("Project does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse editProject(Long id, Project editedProject) {
        Project project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            project = editedProject;
            project.setId(id);
            repo.save(project);
            response.getBody().addData(project);
            response.getBody().setMessage("Project edited");
            return response;
        }
        
        response.getBody().setMessage("Project does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
}
