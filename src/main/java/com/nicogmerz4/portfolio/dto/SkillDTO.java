package com.nicogmerz4.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class SkillDTO {
    private Long id;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 20, message = "El nombre puede tener hasta {max} carácteres.")
    private String name;
    @NotNull(message = "Este campo es obligatorio, no puede estar vacio.")
    @Range(min = 1, max = 100, message = "El porcentaja debe ser de {min} a {max}.")
    private int percentage;
    private String icon;
}
