package com.sanvalero.saludaragon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "location")
public class Location {

    @Schema(description = "Identificador de la localidad", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre de la localidad", example = "Huesca", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Población de la localidad", example = "60000")
    @Column
    private int population;

    @Schema(description = "Extension de la localidad en kilometros cuadrados", example = "161.5")
    @Column
    private float extension;

    @Schema(description = "¿Tiene hospital?", example = "true")
    @NotBlank
    @Column
    private boolean hasHospital;

    @Schema(description = "Fecha de origen de la localidad", example = "08/07/1645")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate originDate;

    @Schema(description = "Listado de Hospitales que tiene esta localidad")
    @OneToMany(mappedBy = "location")
    private List<Hospital> hospitals;
}
