package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.domain.Location;
import com.sanvalero.saludaragon.domain.dto.HospitalDTO;
import com.sanvalero.saludaragon.exception.HospitalNotFoundException;
import com.sanvalero.saludaragon.exception.JobNotFoundException;
import com.sanvalero.saludaragon.exception.LocationNotFoundException;
import com.sanvalero.saludaragon.repository.HospitalRepository;
import com.sanvalero.saludaragon.repository.JobRepository;
import com.sanvalero.saludaragon.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Service
public class HospitalServiceImp implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Set<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public Optional<Hospital> findById(long id) {
        return hospitalRepository.findById(id);
    }

    @Override
    public Set<Employee> findEmployeesBySalaryAndJob(long idHospital, float minSalary, float maxSalary, long idJob) {
        Hospital hospital = hospitalRepository.findById(idHospital)
                .orElseThrow(() -> new HospitalNotFoundException(idHospital));

        Job job = jobRepository.findById(idJob)
                .orElseThrow(() -> new JobNotFoundException(idJob));

        List<Employee> myEmployees = hospital.getEmployees();

        Set<Employee> employees = myEmployees.stream()
                .filter(myEmployee -> myEmployee.getSalary() >= (minSalary))
                .filter(myEmployee -> myEmployee.getSalary() <= (maxSalary))
                .filter(myEmployee -> myEmployee.getJob().equals(job))
                .collect(Collectors.toSet());

        return employees;
    }

    @Override
    public Hospital addHospitalToLocation(long id, HospitalDTO hospitalDTO) {
        Hospital newHospital = new Hospital();
        setHospital(newHospital, hospitalDTO);
        newHospital = hospitalRepository.save(newHospital);
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        newHospital.setLocation(location);

        hospitalRepository.save(newHospital);

        return newHospital;
    }

    @Override
    public Hospital modifyHospital(long id, HospitalDTO hospitalDTO) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));
        setHospital(hospital, hospitalDTO);
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital modifyByBeds(long id, int beds) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));
        hospital.setBeds(beds);
        return hospitalRepository.save(hospital);
    }

    @Override
    public void deleteHospital(long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));
        hospitalRepository.delete(hospital);
    }

    public void setHospital(Hospital hospital, HospitalDTO hospitalDTO) {
        hospital.setName(hospitalDTO.getName());
        hospital.setSpecialties(hospitalDTO.isSpecialties());
        hospital.setBuildDate(hospitalDTO.getBuildDate());
        hospital.setBeds(hospitalDTO.getBeds());
        hospital.setAnnualBudget(hospitalDTO.getAnnualBudget());
    }
}
