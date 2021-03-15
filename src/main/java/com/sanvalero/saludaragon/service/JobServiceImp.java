package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Service
public class JobServiceImp implements JobService{

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Set<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findByCode(String code) {
        return jobRepository.findByCode(code);
    }

//    @Override
//    public Set<Job> findByName(String name) {
//        return jobRepository.findByName(name);
//    }

    @Override
    public Set<Job> findByNameAndSpecialty(String name, String specialty) {
        return jobRepository.findByNameAndSpecialty(name, specialty);
    }

    @Override
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job modifyJob(String code, Job newJob) {
        Job job = jobRepository.findByCode(code);
        newJob.setCode(job.getCode());
        return jobRepository.save(newJob);
    }

    @Override
    public void deleteJob(String code) {
        Job job = jobRepository.findByCode(code);
        jobRepository.delete(job);
    }
}
