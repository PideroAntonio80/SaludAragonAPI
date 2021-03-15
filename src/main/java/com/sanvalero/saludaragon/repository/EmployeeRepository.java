package com.sanvalero.saludaragon.repository;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.domain.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Set<Employee> findAll();
    Employee findById(int id);
    //Set<Employee> findByNameAndSurename(String name, String surename);
}
