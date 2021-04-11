package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.domain.Location;
import com.sanvalero.saludaragon.domain.dto.LocationDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface LocationService {

    Set<Location> findAll();
    Optional<Location> findById(long id);
    Set<Hospital> findByBedsRangeAndHavingSpecialties(long id, int minBeds, int maxBeds, boolean specialties);

    Location addLocation(Location location);
    Location modifyLocation(long id, LocationDTO locationDTO);
    Location modifyLocationByHasHospital(long id, boolean hasHospital);
    void deleteLocation(long id);
}
