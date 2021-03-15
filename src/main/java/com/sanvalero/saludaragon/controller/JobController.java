package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@RestController
@Tag(name = "jobs", description = "Puestos de trabajo en el Salud Aragones")
public class JobController {

    @Autowired
    private JobService jobService;

    @Operation(summary = "Obtiene el listado de puestos de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Job.class)))),
    })

    @GetMapping(value = "/jobs", produces = "application/json")
    public Set<Job> getJobs() {
        return jobService.findAll();
    }

    @Operation(summary = "Obtiene un puesto de trabajo determinado por su código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el puesto", content = @Content(schema = @Schema(implementation = Job.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/jobs/{code}", produces = "application/json")
    public ResponseEntity<Job> getJob(@PathVariable String code) {
        Job job = jobService.findByCode(code);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

//    @GetMapping("/jobs/{name}")
//    public ResponseEntity<Set<Job>> getJobsByName(@PathVariable String name) {
//        Set<Job> myJobs = jobService.findByName(name);
//        return new ResponseEntity<>(myJobs, HttpStatus.OK);
//    }

    @Operation(summary = "Obtiene un puesto de trabajo determinado por su nombre y especialidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el puesto", content = @Content(schema = @Schema(implementation = Job.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/jobs/{name}/{specialty}")
    public ResponseEntity<Set<Job>> getJobsByNameAndSpecialty(@PathVariable String name, @PathVariable String specialty) {
        Set<Job> myJobs = jobService.findByNameAndSpecialty(name, specialty);
        return new ResponseEntity<>(myJobs, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registra el puesto", content = @Content(schema = @Schema(implementation = Job.class)))
    })
    @PostMapping(value = "/jobs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Job> addJob(@RequestBody Job job) {
        Job addedJob = jobService.addJob(job);
        return new ResponseEntity<>(addedJob, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el puesto", content = @Content(schema = @Schema(implementation = Job.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/jobs/{code}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Job> modifyJob(@PathVariable String code, @RequestBody Job newJob) {
        Job job = jobService.modifyJob(code, newJob);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el puesto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/jobs/{code}", produces = "application/json")
    public ResponseEntity<Response> deleteJob(@PathVariable String code) {
        jobService.deleteJob(code);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }
}
