package com.sanvalero.saludaragon.service;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.domain.dto.EmployeeDTO;
import com.sanvalero.saludaragon.exception.EmployeeNotFoundException;
import com.sanvalero.saludaragon.exception.HospitalNotFoundException;
import com.sanvalero.saludaragon.exception.JobNotFoundException;
import com.sanvalero.saludaragon.repository.EmployeeRepository;
import com.sanvalero.saludaragon.repository.HospitalRepository;
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
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Set<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployeeToHospital(long idHospital, long idJob,  EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee();
        setEmployee(newEmployee, employeeDTO);
        newEmployee = employeeRepository.save(newEmployee);
        Hospital hospital = hospitalRepository.findById(idHospital)
                .orElseThrow(() -> new HospitalNotFoundException(idHospital));
        newEmployee.setHospital(hospital);
        Job job = jobRepository.findById(idJob)
                .orElseThrow(() -> new JobNotFoundException(idJob));
        newEmployee.setJob(job);

        employeeRepository.save(newEmployee);

        return newEmployee;
    }

    @Override
    public Employee modifyEmployee(long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        setEmployee(employee, employeeDTO);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee modifyEmployeeBySalary(long id, float salary) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setSalary(salary);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(employee);
    }

    public void setEmployee(Employee employee, EmployeeDTO employeeDTO) {
        employee.setName(employeeDTO.getName());
        employee.setSurename(employeeDTO.getSurename());
        employee.setDateHired(employeeDTO.getDateHired());
        employee.setDateEnd(employeeDTO.getDateEnd());
        employee.setSalary(employeeDTO.getSalary());
        employee.setPermanentContract(employeeDTO.isPermanentContract());
    }
}
