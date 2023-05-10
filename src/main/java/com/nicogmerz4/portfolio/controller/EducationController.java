package com.nicogmerz4.portfolio.controller;

import com.nicogmerz4.portfolio.dto.EducationDTO;
import com.nicogmerz4.portfolio.model.Education;
import com.nicogmerz4.portfolio.service.EducationService;
import com.nicogmerz4.portfolio.service.CustomResponse;
import jakarta.validation.Valid;
import java.text.ParseException;
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

@RequestMapping("/api/educations")
@RestController
public class EducationController {

    @Autowired
    private EducationService service;

    @GetMapping
    public ResponseEntity getEducations() {
        CustomResponse response = service.getEducations();

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEducation(@PathVariable Long id) {
        CustomResponse response = service.findEducation(id);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity createEducation(@RequestBody @Valid EducationDTO newEducation) throws ParseException {
        CustomResponse response = service.createEducation(newEducation);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity findEducation(@PathVariable Long id, @RequestBody @Valid EducationDTO editedEducation) throws ParseException {
        CustomResponse response = service.editEducation(id, editedEducation);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEducation(@PathVariable Long id) {
        CustomResponse response = service.deleteEducation(id);

        return new ResponseEntity(response.getBody(), response.getHttpStatus());
    }
}
