package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.model.Academy;
import com.nicogmerz4.portfolio.repository.AcademyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AcademyService {
    @Autowired
    AcademyRepository repo;
    
    public CustomResponse getAcademies() {
        List<Academy> academies = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        CustomResponse response = new CustomResponse();
        response.getBody().setData(academies);
        return response;
    }
    
    public CustomResponse findAcademy(Long id) {
        Academy academy = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (academy != null) {
            response.getBody().addData(academy);
            return response;
        }
        
        response.getBody().setMessage("Academy not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse createAcademy(Academy newAcademy) {
        Academy academy = repo.save(newAcademy);
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Academy created");
        response.getBody().addData(academy);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteAcademy(Long id) {
        Academy academy = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (academy != null) {
            repo.delete(academy);
            response.getBody().setMessage("Academy deleted");
            return response;
        }
        
        response.getBody().setMessage("Academy does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse editAcademy(Long id, Academy editedAcademy) {
        Academy academy = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (academy != null) {
            academy = editedAcademy;
            academy.setId(id);
            repo.save(academy);
            response.getBody().addData(academy);
            response.getBody().setMessage("Academy edited");
            return response;
        }
        
        response.getBody().setMessage("Academy does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
}
