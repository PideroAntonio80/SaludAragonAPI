package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.domain.dto.JobDTO;
import com.sanvalero.saludaragon.exception.JobNotFoundException;
import com.sanvalero.saludaragon.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public Optional<Job> findById(long id) {
        return jobRepository.findById(id);
    }

    @Override
    public Set<Job> findByName(String name) {
        if(name.equals("")) return jobRepository.findAll();
        else return jobRepository.findByName(name);
    }

    @Override
    public Job findByNameAndSpecialtyAndSurgical(String name, String specialty, boolean surgical) {
        return jobRepository.findByNameAndSpecialtyAndSurgical(name, specialty, surgical);
    }

    @Override
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job modifyJob(long id, JobDTO jobDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        setJob(job, jobDTO);
        return jobRepository.save(job);
    }

    @Override
    public Job modifyJobBySalary(long id, float averageBaseSalary) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        job.setAverageBaseSalary(averageBaseSalary);
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        jobRepository.delete(job);
    }

    public void setJob(Job job, JobDTO jobDTO) {
        job.setName(jobDTO.getName());
        job.setSpecialty(jobDTO.getSpecialty());
        job.setSurgical(jobDTO.isSurgical());
        job.setMirNumber(jobDTO.getMirNumber());
        job.setAverageBaseSalary(jobDTO.getAverageBaseSalary());
        job.setCreation(jobDTO.getCreation());
    }
}
