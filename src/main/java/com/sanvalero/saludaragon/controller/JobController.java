package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Job;
import com.sanvalero.saludaragon.domain.dto.JobDTO;
import com.sanvalero.saludaragon.exception.JobNotFoundException;
import com.sanvalero.saludaragon.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.saludaragon.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 07/03/2021
 */

@RestController
@Tag(name = "jobs", description = "Puestos de trabajo en el Salud Aragones")
public class JobController {

    private final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Obtiene el listado de puestos de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puestos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Job.class)))),
            @ApiResponse(responseCode = "404", description = "Fallo al listar puestos de trabajo",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/jobs", produces = "application/json")
    public ResponseEntity<Set<Job>> getJobs() {

        logger.info("Inicio getJobs");

        Set<Job> jobs = jobService.findAll();

        logger.info("Fin getJobs");

        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Obtiene un puesto de trabajo determinado por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el puesto", content = @Content(schema = @Schema(implementation = Job.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/jobs/{id}", produces = "application/json")
    public ResponseEntity<Job> getJobById(@PathVariable long id) {

        logger.info("Inicio getJobById");

        Job job = jobService.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));

        logger.info("Fin getJobById");

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    /*--------------------------------FIND_BY_NAME----------------------------------*/
    @Operation(summary = "Obtiene una lista de puestos de trabajo de un gremio determinado por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puestos", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Job.class)))),
            @ApiResponse(responseCode = "404", description = "No existen puestos de ese gremio", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/jobs/name", produces = "application/json")
    public ResponseEntity<Set<Job>> getJobsByName(@RequestParam(value = "name", defaultValue = "") String name) {

        logger.info("Inicio getJobsByName");

        Set<Job> myJobs = jobService.findByName(name);

        logger.info("Fin getJobsByName");

        return new ResponseEntity<>(myJobs, HttpStatus.OK);
    }

    /*--------------------------------FIND_BY_NAME_AND_SPECIALTY_AND_SURGICAL----------------------------------*/
    @Operation(summary = "Obtiene un puesto de trabajo determinado por su nombre y especialidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el puesto", content = @Content(schema = @Schema(implementation = Job.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/jobs/name/specialty/surgical", produces = "application/json")
    public ResponseEntity <Job> getJobByNameAndSpecialty(@RequestParam(value = "name", defaultValue = "") String name,
                                                         @RequestParam(value = "specialty", defaultValue = "") String specialty,
                                                         @RequestParam(value = "surgical", defaultValue = "") boolean surgical) {

        logger.info("Inicio getJobByNameAndSpecialty");

        Job myJobs = jobService.findByNameAndSpecialtyAndSurgical(name, specialty, surgical);

        logger.info("Fin getJobByNameAndSpecialty");

        return new ResponseEntity<>(myJobs, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Registra un nuevo puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el puesto", content = @Content(schema = @Schema(implementation = Job.class)))
    })
    @PostMapping(value = "/saludaragon/jobs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Job> addJob(@RequestBody Job job) {

        logger.info("Inicio addJob");

        Job addedJob = jobService.addJob(job);

        logger.info("Fin addJob");

        return new ResponseEntity<>(addedJob, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modifica un puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el puesto", content = @Content(schema = @Schema(implementation = Job.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/saludaragon/jobs/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Job> modifyJob(@PathVariable long id, @RequestBody JobDTO jobDTO) {

        logger.info("Inicio modifyJob");

        Job job = jobService.modifyJob(id, jobDTO);

        logger.info("Fin modifyJob");

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_BY_SALARY----------------------------------*/
    @Operation(summary = "Modifica el salario base de un puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el salario base de este puesto", content = @Content(schema = @Schema(implementation = Job.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/saludaragon/jobs/{id}/salary", produces = "application/json")
    public ResponseEntity<Job> modifyJobBySalary(@PathVariable long id,
                                                 @RequestParam(value = "averageBaseSalary", defaultValue = "") float averageBaseSalary) {

        logger.info("Inicio modifyJobBySalary");

        Job job = jobService.modifyJobBySalary(id, averageBaseSalary);

        logger.info("Fin modifyJobBySalary");

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Elimina un puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el puesto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El puesto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/saludaragon/jobs/{id}")
    public ResponseEntity<Response> deleteJob(@PathVariable long id) {

        logger.info("Inicio deleteJob");

        jobService.deleteJob(id);

        logger.info("Fin deleteJob");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(JobNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(JobNotFoundException jnfe){
        Response response = Response.errorResponse(NOT_FOUND, jnfe.getMessage());
        logger.error(jnfe.getMessage(), jnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
