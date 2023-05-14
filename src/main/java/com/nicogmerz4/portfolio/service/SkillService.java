package com.nicogmerz4.portfolio.service;

import com.nicogmerz4.portfolio.dto.SkillDTO;
import com.nicogmerz4.portfolio.model.Skill;
import com.nicogmerz4.portfolio.repository.SkillRepository;
import com.nicogmerz4.portfolio.utils.ObjectMapperUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SkillService {

    @Autowired
    private SkillRepository repo;
    
    @Autowired
    private ImageStorageService imageStorageService;

    public CustomResponse getSkills() {
        List<Skill> skills = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        CustomResponse response = new CustomResponse();
        response.getBody().setData(skills);
        return response;
    }

    public CustomResponse createSkill(SkillDTO newSkill) {
        Skill skill = new Skill();
        skill.setName(newSkill.getName());
        skill.setPercentage(newSkill.getPercentage());

        skill = repo.save(skill);

        CustomResponse response = new CustomResponse();
        response.getBody().setMessage("Skill created");
        response.getBody().addData(skill);
        response.setHttpStatus(HttpStatus.CREATED);
        return response;
    }

    public CustomResponse deleteSkill(Long id) {
        Skill skill = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();

        if (skill != null) {
            deleteIconIfIsNotNull(skill);
            repo.delete(skill);
            response.getBody().setMessage("Skill deleted");
            return response;
        }

        response.getBody().setMessage("Skill does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public CustomResponse editSkill(Long id, SkillDTO editedSkill) {
        Skill skill = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();

        if (skill != null) {
            skill.setName(editedSkill.getName());
            skill.setPercentage(editedSkill.getPercentage());

            repo.save(skill);

            response.getBody().addData(skill);
            response.getBody().setMessage("Skill edited");
            return response;
        }

        response.getBody().setMessage("Skill does't exist");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public CustomResponse uploadSkillLogo(Long id, MultipartFile file) {
        Skill skill = repo.findById(id).orElse(null);
        CustomResponse response = new CustomResponse();

        deleteIconIfIsNotNull(skill);

        String filename = imageStorageService.store(file);

        skill.setIcon(filename);
        skill = repo.save(skill);

        SkillDTO skillDTO = ObjectMapperUtils.map(skill, SkillDTO.class);

        response.getBody().addData(skillDTO);

        return response;
    }

    public void deleteIconIfIsNotNull(Skill skill) {
        String logo = skill.getIcon();

        if (logo != null) {
            imageStorageService.delete(logo);
        }
    }
}
