package com.sanvalero.saludaragon.domain;

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
@Entity(name = "job")
public class Job {

    @Schema(description = "Codigo del puesto de trabajo", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del puesto de trabajo", example = "medico", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "nombre de la especialidad dentro del puesto", example = "neurologo", required = true)
    @NotBlank
    @Column
    private String specialty;

    @Schema(description = "¿Es una especialidad/puesto quirúrjica?", example = "true")
    @NotBlank
    @Column
    private boolean surgical;

    @Schema(description = "Número de años de residencia", example = "4")
    @NotBlank
    @Column
    private int mirNumber;

    @Schema(description = "Salario medio base de este puesto", example = "1540.87")
    @NotBlank
    @Column
    private float averageBaseSalary;

    @Schema(description = "Fecha de creación de este puesto", example = "19/05/1978")
    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate creation;

    @Schema(description = "Listado de empleados que desempeñan este puesto de trabajo")
    @OneToMany(mappedBy = "job", cascade = CascadeType.REMOVE)
    private List<Employee> employees;

    /*public void includeEmployee(Employee employee) {
        if (employees == null) employees = new ArrayList<>();

        employees.add(employee);
        employee.setJob(this);
    }

    public void removeEmployee(Employee employee) {

        employees.remove(employee);
    }*/
}
