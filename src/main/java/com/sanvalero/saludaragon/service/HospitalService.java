package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.domain.dto.HospitalDTO;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface HospitalService {

    Set<Hospital> findAll();
    Optional<Hospital> findById(long id);
    Set<Employee> findEmployeesBySalaryAndJob(long idHospital, float minSalary, float maxSalary, long idJob);

    Hospital addHospitalToLocation(long id, HospitalDTO hospitalDTO);
    Hospital modifyHospital(long id, HospitalDTO hospitalDTO);
    Hospital modifyByBeds(long id, int beds);
    void deleteHospital(long id);
}
