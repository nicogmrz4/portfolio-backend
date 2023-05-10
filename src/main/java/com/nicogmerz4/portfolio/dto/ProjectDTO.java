package com.nicogmerz4.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String image;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 20, message = "El título puedete tener hasta {max} carácteres.")
    private String title;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Size(max = 220, message = "La descripción no puede tener mas de {max} carácteres.")
    private String description;
    @NotBlank(message = "Este campo es obligatorio, no puede estar vacio.")
    @Pattern(regexp = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}", message = "Formato de fecha invalido. El formado debe ser dd/mm/yyyy")
    private String createdAt;
}
