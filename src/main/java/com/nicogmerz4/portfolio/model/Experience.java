package com.nicogmerz4.portfolio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String company;
    private String description;
    private String job;
    @JsonFormat(pattern="MM/yyyy")
    private Date periodFrom;
    @JsonFormat(pattern="MM/yyyy")
    private Date periodAt;
}
