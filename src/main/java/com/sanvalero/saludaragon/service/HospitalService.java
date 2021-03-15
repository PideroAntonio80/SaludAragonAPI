package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Hospital;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface HospitalService {

    Set<Hospital> findAll();
    Hospital findById(int id);

    Hospital addHospital(Hospital hospital);
    Hospital modifyHospital(int id, Hospital newHospital);
    void deleteHospital(int id);
}
