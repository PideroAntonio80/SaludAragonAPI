package com.sanvalero.saludaragon.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 02/04/2021
 */
public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super();
    }

    public JobNotFoundException(String message){
        super(message);
    }

    public JobNotFoundException(long id){
        super("Job not found: " + id);
    }
}
