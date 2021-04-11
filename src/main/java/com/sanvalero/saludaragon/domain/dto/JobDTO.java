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
public class JobDTO {

    @Schema(description = "Nombre del puesto de trabajo", example = "medico", required = true)
    private String name;

    @Schema(description = "nombre de la especialidad dentro del puesto", example = "neurologo", required = true)
    private String specialty;

    @Schema(description = "¿Es una especialidad/puesto quirúrjica?", example = "true")
    private boolean surgical;

    @Schema(description = "Número de años de residencia", example = "4")
    private int mirNumber;

    @Schema(description = "Salario medio base de este puesto", example = "1540.87")
    private float averageBaseSalary;

    @Schema(description = "Fecha de creación de este puesto", example = "19/05/1978")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creation;
}
