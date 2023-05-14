package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.dto.EducationDTO;
import com.nicogmerz4.portfolio.model.Education;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.nicogmerz4.portfolio.repository.EducationRepository;
import com.nicogmerz4.portfolio.utils.ObjectMapperUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EducationService {
    @Autowired
    EducationRepository repo;
    
    @Autowired
    ImageStorageService imageStorageService;
    
    public CustomResponse getEducations() {
        List<Education> educations = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<EducationDTO> educationsDTO = ObjectMapperUtils.mapAll(educations, EducationDTO.class);
        CustomResponse response = new CustomResponse();
        response.getBody().setData(educationsDTO);
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
        
        education = repo.save(education);
        
        newEducation.setId(education.getId());
        
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Education created");
        response.getBody().addData(newEducation);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }
    
    public CustomResponse deleteEducation(Long id) {
        Education education = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
    
        if (education != null) {
            deleteLogoIfIsNotNull(education);
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
            
            editedEducation.setId(id);
            
            response.getBody().addData(editedEducation);
            response.getBody().setMessage("Education edited");
            return response;
        }
        
        response.getBody().setMessage("Education does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
    
        public CustomResponse uploadEducationLogo(Long id, MultipartFile file) {
        Education education = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();
        
        deleteLogoIfIsNotNull(education);
        
        String filename = imageStorageService.store(file);

        education.setLogo(filename);            
        education = repo.save(education);

        EducationDTO educationDTO = ObjectMapperUtils.map(education, EducationDTO.class);
        
        response.getBody().addData(educationDTO);
        
        return response;
    }
    
    public void deleteLogoIfIsNotNull(Education education) {
        String logo = education.getLogo();
        
        if (logo != null) {
            imageStorageService.delete(logo);
        }
    }
}
