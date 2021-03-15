package com.sanvalero.saludaragon.domain;

import lombok.*;

import javax.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String surename;
    @Column
    private LocalDate dateHired;
    @Column
    private LocalDate dateEnd;
    @Column
    private int idHospital;
    @Column
    private String jobCode;
}
