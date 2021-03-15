package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 14/03/2021
 */

@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/hospitals")
    public Set<Hospital> getHospitals() {
        return hospitalService.findAll();
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable int id) {
        Hospital hospital = hospitalService.findById(id);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @PostMapping("/hospitals")
    public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital) {
        Hospital addedHospital = hospitalService.addHospital(hospital);
        return new ResponseEntity<>(addedHospital, HttpStatus.CREATED);
    }

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> modifyHospital(@PathVariable int id, @RequestBody Hospital newHospital) {
        Hospital hospital = hospitalService.modifyHospital(id, newHospital);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @DeleteMapping("/hospitals/{id}")
    public ResponseEntity<Response> deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }
}
