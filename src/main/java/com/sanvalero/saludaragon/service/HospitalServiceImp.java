package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Service
public class HospitalServiceImp implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Set<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital findById(int id) {
        return hospitalRepository.findById(id);
    }

    @Override
    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital modifyHospital(int id, Hospital newHospital) {
        Hospital hospital = hospitalRepository.findById(id);
        newHospital.setId(hospital.getId());
        return hospitalRepository.save(newHospital);
    }

    @Override
    public void deleteHospital(int id) {
        hospitalRepository.findById(id);
        hospitalRepository.deleteById((long) id);
    }
}
