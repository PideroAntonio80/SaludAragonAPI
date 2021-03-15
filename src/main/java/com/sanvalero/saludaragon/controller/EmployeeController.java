package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 14/03/2021
 */

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Set<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*@GetMapping("/employees")
    public ResponseEntity<Set<Employee>> getEmployeeByNameAndSurename(@RequestParam(value = "name", defaultValue = "") String name,
                                                                      @RequestParam(value = "surename", defaultValue = "") String surename) {
        Set<Employee> myEmployees = null;
        if(name.equals("") || surename.equals("")) myEmployees = employeeService.findAll();
        else myEmployees = employeeService.findByNameAndSurename(name, surename);
        return new ResponseEntity<>(myEmployees, HttpStatus.OK);
    }*/

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee addedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> modifyJob(@PathVariable int id, @RequestBody Employee newEmployee) {
        Employee employee = employeeService.modifyEmployee(id, newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Response> deleteJob(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }
}
