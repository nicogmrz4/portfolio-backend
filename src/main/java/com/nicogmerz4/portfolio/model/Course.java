package com.nicogmerz4.portfolio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Setter @Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private String description;
    private String certificade;
    @JsonFormat(pattern="MM/yyyy")
    private Date periodFrom;
    @JsonFormat(pattern="MM/yyyy")
    private Date periodAt;
}
