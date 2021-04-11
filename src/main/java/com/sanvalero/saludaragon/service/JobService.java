package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.domain.dto.JobDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface JobService {
    Set<Job> findAll();
    Optional<Job> findById(long id);
    Set<Job> findByName(String name);
    Job findByNameAndSpecialtyAndSurgical(String name, String specialty, boolean surgical);

    Job addJob(Job job);

    Job modifyJob(long id, JobDTO jobDTO);

    Job modifyJobBySalary(long id, float averageBaseSalary);

    void deleteJob(long id);
}
