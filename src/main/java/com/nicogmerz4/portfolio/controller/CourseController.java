package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.model.Course;
import com.nicogmerz4.portfolio.service.CourseService;
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

@RequestMapping("/api/courses")
@RestController
public class CourseController {
        
    @Autowired
    private CourseService service;
    
    @GetMapping
    public ResponseEntity getCourses() {
        CustomResponse response = service.getCourses();
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findCourse(@PathVariable Long id) {
        CustomResponse response = service.findCourse(id);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PostMapping
    public ResponseEntity createCourse(@RequestBody Course newCourse){
        CustomResponse response = service.createCourse(newCourse);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity findCourse(@PathVariable Long id, @RequestBody Course editedCourse) {
        CustomResponse response = service.editCourse(id, editedCourse);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        CustomResponse response = service.deleteCourse(id);
        
        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
