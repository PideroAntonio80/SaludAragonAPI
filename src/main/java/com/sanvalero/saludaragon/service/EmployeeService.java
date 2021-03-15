package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Employee;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface EmployeeService {

    Set<Employee> findAll();
    Employee findById(int id);
    //Set<Employee> findByNameAndSurename(String name, String surename);

    Employee addEmployee(Employee employee);

    Employee modifyEmployee(int id, Employee newEmployee);

    void deleteEmployee(int id);
}
