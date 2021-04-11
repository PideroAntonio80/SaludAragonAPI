package com.sanvalero.saludaragon.repository;

import com.sanvalero.saludaragon.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    Set<Location> findAll();
}
