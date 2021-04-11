package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.domain.dto.EmployeeDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

public interface EmployeeService {

    Set<Employee> findAll();
    Optional<Employee> findById(long id);

    Employee addEmployeeToHospital(long idHospital, long idJob, EmployeeDTO employeeDTO);

    Employee modifyEmployee(long id, EmployeeDTO employeeDTO);

    Employee modifyEmployeeBySalary(long id, float salary);

    void deleteEmployee(long id);
}
