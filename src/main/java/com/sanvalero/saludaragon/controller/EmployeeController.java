package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Employee;
import com.sanvalero.saludaragon.domain.dto.EmployeeDTO;
import com.sanvalero.saludaragon.exception.EmployeeNotFoundException;
import com.sanvalero.saludaragon.service.EmployeeService;
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
@Tag(name = "employees", description = "Información de los empleados")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Obtiene el listado de empleados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de empleados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class)))),
            @ApiResponse(responseCode = "404", description = "Fallo al listar empleados",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/employees", produces = "application/json")
    public ResponseEntity<Set<Employee>> getEmployees() {

        logger.info("Inicio getEmployees");

        Set<Employee> employees = employeeService.findAll();

        logger.info("Fin getEmployees");

        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Obtiene un empleado determinado por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el empleado", content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "El empleado no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/employees/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {

        logger.info("Inicio getEmployeeById");

        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        logger.info("Fin getEmployeeById");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Registra un nuevo empleado en un hospital con un puesto de trabajo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra un nuevo empleado",
                    content = @Content(schema = @Schema(implementation = Employee.class)))
    })
    @PostMapping(value = "/saludaragon/hospitals/{idHospital}/jobs/{idJob}/employee", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Employee> addEmployeeToHospital(@PathVariable long idHospital,
                                                          @PathVariable long idJob,
                                                          @RequestBody EmployeeDTO employeeDTO) {

        logger.info("Inicio addEmployeeToHospital");

        Employee addedEmployee = employeeService.addEmployeeToHospital(idHospital, idJob, employeeDTO);

        logger.info("Fin addEmployeeToHospital");

        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modifica un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el empleado", content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "El empleado no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/saludaragon/employees/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {

        logger.info("Inicio modifyEmployee");

        Employee employee = employeeService.modifyEmployee(id, employeeDTO);

        logger.info("Fin modifyEmployee");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_SALARY----------------------------------*/
    @Operation(summary = "Modifica un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el sueldo del empleado", content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "El empleado no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/saludaragon/employees/{id}/salary", produces = "application/json")
    public ResponseEntity<Employee> modifyEmployeeBySalary(@PathVariable long id,
                                                           @RequestParam(value = "salary", defaultValue = "") float salary) {

        logger.info("Inicio modifyEmployeeBySalary");

        Employee employee = employeeService.modifyEmployeeBySalary(id, salary);

        logger.info("Fin modifyEmployeeBySalary");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Elimina un empleado de un hospital")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina empleado", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El empleado no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/saludaragon/employees/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable long id) {

        logger.info("Inicio deleteEmployee");

        employeeService.deleteEmployee(id);

        logger.info("Fin deleteEmployee");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(EmployeeNotFoundException enfe){
        Response response = Response.errorResponse(NOT_FOUND, enfe.getMessage());
        logger.error(enfe.getMessage(), enfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
