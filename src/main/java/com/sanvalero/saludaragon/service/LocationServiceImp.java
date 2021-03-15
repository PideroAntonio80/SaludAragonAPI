package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Location;
import com.sanvalero.saludaragon.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Set<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(int id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location modifyLocation(int id, Location newLocation) {
        Location location = locationRepository.findById(id);
        newLocation.setId(location.getId());
        return locationRepository.save(newLocation);
    }

    @Override
    public void deleteLocation(int id) {
        locationRepository.findById(id);
        locationRepository.deleteById((long) id);
    }
}
