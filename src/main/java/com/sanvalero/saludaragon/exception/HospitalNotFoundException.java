package com.sanvalero.saludaragon.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 02/04/2021
 */
public class HospitalNotFoundException extends RuntimeException {

    public HospitalNotFoundException() {
        super();
    }

    public HospitalNotFoundException(String message){
        super(message);
    }

    public HospitalNotFoundException(long id){
        super("Hospital not found: " + id);
    }
}
