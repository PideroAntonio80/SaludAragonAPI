package com.sanvalero.saludaragon.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 05/04/2021
 */

@Data
@NoArgsConstructor
public class EmployeeDTO {

    @Schema(description = "Nombre del empleado", example = "José", required = true)
    private String name;

    @Schema(description = "Apellidos del empleado", example = "Pérez López", required = true)
    private String surename;

    @Schema(description = "Fecha de contratación del empleado", example = "28/12/2012", required = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateHired;

    @Schema(description = "Fecha de fin contrato del empleado", example = "22/11/2018")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateEnd;

    @Schema(description = "Salario del empleado", example = "1450.5")
    private float salary;

    @Schema(description = "¿Contrato Permanente?", example = "true")
    private boolean permanentContract;
}
