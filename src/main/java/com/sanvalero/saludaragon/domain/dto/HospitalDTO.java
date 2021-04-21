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
public class HospitalDTO {

    @Schema(description = "Nombre del Hospital", example = "Miguel Servet", required = true)
    private String name;

    @Schema(description = "¿Tiene especialidades médicas?", example = "true")
    private boolean specialties;

    @Schema(description = "Fecha de construcción del hospital", example = "26/04/1971")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate buildDate;

    @Schema(description = "Número de camas del hospital", example = "350")
    private int beds;

    @Schema(description = "Presupuesto anual del hospital", example = "15654876.96")
    private float annualBudget;
}
