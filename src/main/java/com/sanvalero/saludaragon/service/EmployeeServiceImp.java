package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Set<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    /*@Override
    public Set<Employee> findByNameAndSurename(String name, String surename) {
        return employeeRepository.findByNameAndSurename(name, surename);
    }*/

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee modifyEmployee(int id, Employee newEmployee) {
        Employee employee = employeeRepository.findById(id);
        newEmployee.setId(employee.getId());
        return employeeRepository.save(newEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.findById(id);
        employeeRepository.deleteById((long) id);
    }
}
