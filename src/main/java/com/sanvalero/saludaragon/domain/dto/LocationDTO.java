package com.sanvalero.saludaragon.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 05/04/2021
 */

@Data
@NoArgsConstructor
public class LocationDTO {

    @Schema(description = "Nombre de la localidad", example = "Huesca", required = true)
    private String name;

    @Schema(description = "Población de la localidad", example = "60000")
    private int population;

    @Schema(description = "Extension de la localidad en kilometros cuadrados", example = "161.5")
    private float extension;

    @Schema(description = "¿Tiene hospital?", example = "true")
    private boolean hasHospital;

    @Schema(description = "Fecha de origen de la localidad", example = "08/07/1645")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate originDate;
}
