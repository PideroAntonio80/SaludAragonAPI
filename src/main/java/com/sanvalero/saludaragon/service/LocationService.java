package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Location;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface LocationService {

    Set<Location> findAll();
    Location findById(int id);

    Location addLocation(Location location);
    Location modifyLocation(int id, Location location);
    void deleteLocation(int id);
}
