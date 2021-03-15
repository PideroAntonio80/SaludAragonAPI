package com.sanvalero.saludaragon.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "job")

public class Job {

    @Schema(description = "Codigo del puesto de trabajo", example = "A003", required = true)
    @Id
    private String code;
    @Schema(description = "Nombre del puesto de trabajo", example = "medico", required = true)
    @NotBlank
    @Column
    private String name;
    @Schema(description = "nombre de la especialidad dentro del puesto", example = "neurologo", required = true)
    @NotBlank
    @Column
    private String specialty;
}
