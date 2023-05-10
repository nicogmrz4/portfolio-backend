package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nicogmerz4.portfolio.repository.ProjectRepository;
import com.nicogmerz4.portfolio.model.Project;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repo;
    
    public CustomResponse getProjects() {
        List<Project> projects = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
    
    public CustomResponse createProject(ProjectDTO newProject) throws ParseException {
        Project project = new Project();
        project.setTitle(newProject.getTitle());
        project.setDescription(newProject.getDescription());
        project.setCreatedAt(new SimpleDateFormat("dd/MM/yyyy").parse(newProject.getCreatedAt()));
        
        project = repo.save(project);
        
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
    
    public CustomResponse editProject(Long id, ProjectDTO editedProject) throws ParseException {
        Project project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            project.setTitle(editedProject.getTitle());
            project.setDescription(editedProject.getDescription());
            project.setCreatedAt(new SimpleDateFormat("dd/MM/yyyy").parse(editedProject.getCreatedAt()));

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
