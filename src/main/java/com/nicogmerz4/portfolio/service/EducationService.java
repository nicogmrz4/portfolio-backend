package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.dto.EducationDTO;
import com.nicogmerz4.portfolio.model.Education;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.nicogmerz4.portfolio.repository.EducationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class EducationService {
    @Autowired
    EducationRepository repo;
    
    public CustomResponse getEducations() {
        List<Education> academies = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        CustomResponse response = new CustomResponse();
        response.getBody().setData(academies);
        return response;
    }
    
    public CustomResponse findEducation(Long id) {
        Education education = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (education != null) {
            response.getBody().addData(education);
            return response;
        }
        
        response.getBody().setMessage("Education not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse createEducation(EducationDTO newEducation) throws ParseException {
        Education education = new Education();
        education.setName(newEducation.getName());
        education.setCareer(newEducation.getCareer());
        education.setDescription(newEducation.getDescription());
        education.setPeriodFrom(new SimpleDateFormat("MM/yyyy").parse(newEducation.getPeriodFrom()));
        education.setPeriodAt(new SimpleDateFormat("MM/yyyy").parse(newEducation.getPeriodAt()));
            
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Education created");
        response.getBody().addData(education);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteEducation(Long id) {
        Education education = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (education != null) {
            repo.delete(education);
            response.getBody().setMessage("Education deleted");
            return response;
        }
        
        response.getBody().setMessage("Education does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
    public CustomResponse editEducation(Long id, EducationDTO editedEducation) throws ParseException {
        Education education = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (education != null) {
            education.setName(editedEducation.getName());
            education.setCareer(editedEducation.getCareer());
            education.setDescription(editedEducation.getDescription());
            education.setPeriodFrom(new SimpleDateFormat("MM/yyyy").parse(editedEducation.getPeriodFrom()));
            education.setPeriodAt(new SimpleDateFormat("MM/yyyy").parse(editedEducation.getPeriodAt()));
            
            repo.save(education);
            
            response.getBody().addData(education);
            response.getBody().setMessage("Education edited");
            return response;
        }
        
        response.getBody().setMessage("Education does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
}
