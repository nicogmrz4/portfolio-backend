package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.model.Course;
import com.nicogmerz4.portfolio.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseRepository repo;
    
    public CustomResponse getCourses() {
        List<Course> projects = repo.findAll();
        CustomResponse response = new CustomResponse();
        response.getBody().setData(projects);
        return response;
    }
    
    public CustomResponse findCourse(Long id) {
        Course project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            response.getBody().addData(project);
            return response;
        }
        
        response.getBody().setMessage("Course not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse createCourse(Course newCourse) {
        Course project = repo.save(newCourse);
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Course created");
        response.getBody().addData(project);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteCourse(Long id) {
        Course project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            repo.delete(project);
            response.getBody().setMessage("Course deleted");
            return response;
        }
        
        response.getBody().setMessage("Course does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse editCourse(Long id, Course editedCourse) {
        Course project = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (project != null) {
            project = editedCourse;
            project.setId(id);
            repo.save(project);
            response.getBody().addData(project);
            response.getBody().setMessage("Course edited");
            return response;
        }
        
        response.getBody().setMessage("Course does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }

}
