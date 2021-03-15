package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Location;
import com.sanvalero.saludaragon.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 15/03/2021
 */

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public Set<Location> getLocations() {
        return locationService.findAll();
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationService.findById(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        Location addedLocation = locationService.addLocation(location);
        return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> modifyLocation(@PathVariable int id, @RequestBody Location newLocation) {
        Location location = locationService.modifyLocation(id, newLocation);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Response> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }
}
