package com.sanvalero.saludaragon.repository;

import com.sanvalero.saludaragon.domain.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    Set<Job> findAll();
    Job findByCode(String code);
//    Set<Job> findByName(String name);
    Set<Job> findByNameAndSpecialty(String name, String specialty);
}
