package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.domain.Location;
import com.sanvalero.saludaragon.domain.dto.LocationDTO;
import com.sanvalero.saludaragon.exception.LocationNotFoundException;
import com.sanvalero.saludaragon.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Optional<Location> findById(long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Set<Hospital> findByBedsRangeAndHavingSpecialties(long id, int minBeds, int maxBeds, boolean specialties) {
        Location location = findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        List<Hospital> myHospitals = location.getHospitals();
        Set<Hospital> hospitals = myHospitals.stream()
                .filter(myHospital -> myHospital.getBeds() >= minBeds)
                .filter(myHospital -> myHospital.getBeds() <= maxBeds)
                .filter(myHospital -> myHospital.isSpecialties() == specialties)
                .collect(Collectors.toSet());
        return hospitals;
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location modifyLocation(long id, LocationDTO locationDTO) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        setLocation(location, locationDTO);
        return locationRepository.save(location);
    }

    @Override
    public Location modifyLocationByHasHospital(long id, boolean hasHospital) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        location.setHasHospital(hasHospital);
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        locationRepository.delete(location);
    }

    public void setLocation(Location location, LocationDTO locationDTO) {
        location.setName(locationDTO.getName());
        location.setPopulation(locationDTO.getPopulation());
        location.setExtension(locationDTO.getExtension());
        location.setHasHospital(locationDTO.isHasHospital());
        location.setOriginDate(locationDTO.getOriginDate());
    }
}
