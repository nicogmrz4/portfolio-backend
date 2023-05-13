package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.utils.ObjectMapperUtils;
import com.nicogmerz4.portfolio.dto.ExperienceDTO;
import com.nicogmerz4.portfolio.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nicogmerz4.portfolio.model.Experience;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

@Service
public class ExperienceService {
    @Autowired
    ExperienceRepository repo;

    public CustomResponse getExperiences() {
        List<Experience> experiences = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ExperienceDTO> experiencesDTO = ObjectMapperUtils.mapAll(experiences, ExperienceDTO.class);
        CustomResponse response = new CustomResponse();
        response.getBody().setData(experiencesDTO);
        return response;
    }

    public CustomResponse findExperience(Long id) {
        Experience experience = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();

        if (experience != null) {
            response.getBody().addData(experience);
            return response;
        }

        response.getBody().setMessage("Experience not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public CustomResponse createExperience(ExperienceDTO newExperience) throws ParseException {
        Experience experience = new Experience();
        experience.setCompany(newExperience.getCompany());
        experience.setJob(newExperience.getJob());
        experience.setDescription(newExperience.getDescription());
        experience.setPeriodFrom(new SimpleDateFormat("MM/yyyy").parse(newExperience.getPeriodFrom()));
        experience.setPeriodAt(new SimpleDateFormat("MM/yyyy").parse(newExperience.getPeriodAt()));

        experience = repo.save(experience);
        
        newExperience.setId(experience.getId());
        
        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Experience created");
        response.getBody().addData(newExperience);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }

    public CustomResponse deleteExperience(Long id) {
        Experience experience = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();

        if (experience != null) {
            repo.delete(experience);
            response.getBody().setMessage("Experience deleted");
            return response;
        }

        response.getBody().setMessage("Experience does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public CustomResponse editExperience(Long id, ExperienceDTO editedExperience) throws ParseException {
        Experience experience = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();

        if (experience != null) {

            experience.setCompany(editedExperience.getCompany());
            experience.setJob(editedExperience.getJob());
            experience.setDescription(editedExperience.getDescription());
            experience.setPeriodFrom(new SimpleDateFormat("MM/yyyy").parse(editedExperience.getPeriodFrom()));
            experience.setPeriodAt(new SimpleDateFormat("MM/yyyy").parse(editedExperience.getPeriodAt()));

            repo.save(experience);
    
            editedExperience.setId(id);

            response.getBody().addData(editedExperience);
            response.getBody().setMessage("Experience edited");
            return response;
        }

        response.getBody().setMessage("Experience does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }
}
