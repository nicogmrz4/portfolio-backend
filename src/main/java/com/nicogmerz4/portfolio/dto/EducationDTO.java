package com.nicogmerz4.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EducationDTO {
    private Long id;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 30, message = "El nombre del instituto puede tener hasta {max} carácteres.")
    private String name;
    private String logo;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 30, message = "La carrera puede tener hasta {max} carácteres.")
    private String career;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 220, message = "La descripción puede tener hasta {max} carácteres.")
    private String description;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Pattern(regexp = "[0-9]{1,2}/[0-9]{4}", message = "Formato de fecha invalido. El formado debe ser mm/yyyy")
    private String periodFrom;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Pattern(regexp = "[0-9]{1,2}/[0-9]{4}", message = "Formato de fecha invalido. El formado debe ser mm/yyyy")
    private String periodAt;
}
