package com.nicogmerz4.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExperienceDTO {
    private Long id;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 30, message = "El nombre de la empresa puede tener hasta {max} carácteres.")
    private String company;
    private String logo;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 20, message = "El puesto puede tener hasta {max} carácteres.")
    private String job;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 220, message = "La descripción puede tener hasta {max} carácteres.")
    private String description;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Pattern(regexp = "([1-9]{1}[0-2]{0,1})/[0-9]{4}", message = "Formato de fecha invalido. El formado debe ser m/yyyy")
    private String periodFrom;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Pattern(regexp = "([1-9]{1}[0-2]{0,1})/[0-9]{4}", message = "Formato de fecha invalido. El formado debe ser m/yyyy")
    private String periodAt;
}
