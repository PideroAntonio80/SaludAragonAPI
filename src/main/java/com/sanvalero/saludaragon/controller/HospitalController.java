package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.domain.dto.HospitalDTO;
import com.sanvalero.saludaragon.exception.HospitalNotFoundException;
import com.sanvalero.saludaragon.service.HospitalService;
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
 * el 14/03/2021
 */

@RestController
@Tag(name = "hospitals", description = "Información de los hospitales")
public class HospitalController {

    private final Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @Autowired
    private HospitalService hospitalService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Obtiene el listado de Hospitales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de hospitales",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hospital.class)))),
            @ApiResponse(responseCode = "404", description = "Fallo al listar hospitales",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/hospitals", produces = "application/json")
    public ResponseEntity<Set<Hospital>> getHospitals() {

        logger.info("Inicio getHospitals");

        Set<Hospital> hospitals = hospitalService.findAll();

        logger.info("Fin getHospitals");

        return ResponseEntity.status(HttpStatus.OK).body(hospitals);

    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Obtiene un Hospital determinado por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el hospital", content = @Content(schema = @Schema(implementation = Hospital.class))),
            @ApiResponse(responseCode = "404", description = "El hospital no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/hospitals/{id}", produces = "application/json")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable long id) {

        logger.info("Inicio getHospitalById");

        Hospital hospital = hospitalService.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));

        logger.info("Fin getHospitalById");

        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    /*--------------------------------FIND_EMPLOYEES_BY_DATE_AND_JOB----------------------------------*/
    @Operation(summary = "Obtiene los empleados de una hospital que ganan un salario dentro de un determinado rango y filtrados por puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de empleados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class)))),
            @ApiResponse(responseCode = "404", description = "No hay empleados con esas características en este hospital",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/hospitals/{idHospital}/jobs/{idJob}/employeessalaryjob", produces = "application/json")
    public ResponseEntity<Set<Employee>> findEmployeesFromHospital(@PathVariable long idHospital,
                                                                   @PathVariable long idJob,
                                                                   @RequestParam(value = "minSalary", defaultValue = "") float minSalary,
                                                                   @RequestParam(value = "maxSalary", defaultValue = "") float maxSalary) {

        logger.info("Inicio findEmployeesFromHospital");

        Set<Employee> myEmployees = hospitalService.findEmployeesBySalaryAndJob(idHospital, minSalary, maxSalary, idJob);

        logger.info("Fin findEmployeesFromHospital");

        return new ResponseEntity<>(myEmployees, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Registra un nuevo hospital en una localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra un nuevo hospital",
                    content = @Content(schema = @Schema(implementation = Hospital.class)))
    })
    @PostMapping(value = "/saludaragon/locations/{id}/hospital", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Hospital> addHospital(@PathVariable long id, @RequestBody HospitalDTO hospitalDTO) {

        logger.info("Inicio addHospital");

        Hospital addedHospital = hospitalService.addHospitalToLocation(id, hospitalDTO);

        logger.info("Fin addHospital");

        return new ResponseEntity<>(addedHospital, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modifica un hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el hospital", content = @Content(schema = @Schema(implementation = Hospital.class))),
            @ApiResponse(responseCode = "404", description = "El hospital no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/saludaragon/hospitals/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Hospital> modifyHospital(@PathVariable long id, @RequestBody HospitalDTO hospitalDTO) {

        logger.info("Inicio modifyHospital");

        Hospital hospital = hospitalService.modifyHospital(id, hospitalDTO);

        logger.info("Fin modifyHospital");

        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_BY_BEDS----------------------------------*/
    @Operation(summary = "Modifica el número de camas de un hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifican las camas del hospital", content = @Content(schema = @Schema(implementation = Hospital.class))),
            @ApiResponse(responseCode = "404", description = "El hospital no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/saludaragon/hospitals/{id}/beds", produces = "application/json")
    public ResponseEntity<Hospital> modifyHospitalByBeds(@PathVariable long id,
                                                         @RequestParam(value = "beds", defaultValue = "") int beds) {

        logger.info("Inicio modifyHospitalByBeds");

        Hospital hospital = hospitalService.modifyByBeds(id, beds);

        logger.info("Fin modifyHospitalByBeds");

        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Elimina un hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el hospital", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El hospital no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/saludaragon/hospitals/{id}")
    public ResponseEntity<Response> deleteHospital(@PathVariable long id) {

        logger.info("Inicio deleteHospital");

        hospitalService.deleteHospital(id);

        logger.info("Fin deleteHospital");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(HospitalNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(HospitalNotFoundException hnfe){
        Response response = Response.errorResponse(NOT_FOUND, hnfe.getMessage());
        logger.error(hnfe.getMessage(), hnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
