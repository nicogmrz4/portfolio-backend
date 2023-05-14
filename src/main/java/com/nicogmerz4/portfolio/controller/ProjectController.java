package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.dto.ProjectDTO;
import com.nicogmerz4.portfolio.service.CustomResponse;
import com.nicogmerz4.portfolio.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nicogmerz4.portfolio.service.ProjectService;
import jakarta.validation.Valid;
import java.text.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity getProjects() {
        CustomResponse response = service.getProjects();

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity findProject(@PathVariable Long id) {
        CustomResponse response = service.findProject(id);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity createProject(@RequestBody @Valid ProjectDTO newProject) throws ParseException {
        CustomResponse response = service.createProject(newProject);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity findProject(@PathVariable Long id, @RequestBody @Valid ProjectDTO editedProject) throws ParseException {
        CustomResponse response = service.editProject(id, editedProject);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        CustomResponse response = service.deleteProject(id);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @PostMapping("/{id}/image")
    public ResponseEntity uploadExperienceLogo(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        CustomResponse response = service.uploadProjectImage(id, file);
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
