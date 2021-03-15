package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Job;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface JobService {
    Set<Job> findAll();
    Job findByCode(String code);
//    Set<Job> findByName(String name);
    Set<Job> findByNameAndSpecialty(String name, String specialty);

    Job addJob(Job job);

    Job modifyJob(String code, Job newJob);

    void deleteJob(String Code);
}
