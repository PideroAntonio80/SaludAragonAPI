package com.sanvalero.saludaragon.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "hospital")
public class Hospital {

    @Schema(description = "Identificador del Hospital", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del Hospital", example = "Miguel Servet", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "¿Tiene especialidades médicas?", example = "true")
    @NotBlank
    @Column
    private boolean specialties;

    @Schema(description = "Fecha de construcción del hospital", example = "26/04/1978")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate buildDate;

    @Schema(description = "Número de camas del hospital", example = "350")
    @NotBlank
    @Column
    private int beds;

    @Schema(description = "Presupuesto anual del hospital", example = "15654876.96")
    @NotBlank
    @Column
    private float annualBudget;

    @Schema(description = "identificador de la localidad a la que pertenece este hospital")
    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;

    @Schema(description = "Listado de empleados que tiene este hospital")
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.REMOVE)
    private List<Employee> employees;
}
