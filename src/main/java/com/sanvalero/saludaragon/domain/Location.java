package com.sanvalero.saludaragon.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "location")

public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int population;
}
