package com.nicogmerz4.portfolio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String career;
    private String logo;
    private String description;
    private Date periodFrom;    
    private Date periodAt;
}
