package com.sanvalero.saludaragon.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 02/04/2021
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }

    public EmployeeNotFoundException(long id){
        super("Employee not found: " + id);
    }
}
