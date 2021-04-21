package com.sanvalero.saludaragon.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "employee")
public class Employee {

    @Schema(description = "Identificador del empleado", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del empleado", example = "José", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Apellidos del empleado", example = "Pérez López", required = true)
    @NotBlank
    @Column
    private String surename;

    @Schema(description = "Fecha de contratación del empleado", example = "28/12/2012", required = true)
    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate dateHired;

    @Schema(description = "Fecha de fin contrato del empleado", example = "22/11/2018")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate dateEnd;

    @Schema(description = "Salario del empleado", example = "1450.5")
    @NotBlank
    @Column
    private float salary;

    @Schema(description = "¿Contrato Permanente?", example = "true")
    @NotBlank
    @Column
    private boolean permanentContract;

    @Schema(description = "Hospital al que pertenece este empleado")
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonBackReference
    private Hospital hospital;

    @Schema(description = "Puesto que desempeña este empleado")
    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonBackReference(value = "use-movement")
    private Job job;
}
