package com.sanvalero.saludaragon.controller;

import com.sanvalero.saludaragon.domain.Hospital;
import com.sanvalero.saludaragon.domain.Location;
import com.sanvalero.saludaragon.domain.dto.LocationDTO;
import com.sanvalero.saludaragon.exception.LocationNotFoundException;
import com.sanvalero.saludaragon.service.LocationService;
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
 * el 15/03/2021
 */

@RestController
@Tag(name = "locations", description = "Informacion de las localidades")
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Obtiene el listado de todas las Localidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de localidades",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Location.class)))),
            @ApiResponse(responseCode = "404", description = "Fallo al listar localidades",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/locations", produces = "application/json")
    public ResponseEntity<Set<Location>> getLocations() {

        logger.info("Inicio getLocations");

        Set<Location> locations = locationService.findAll();

        logger.info("Fin getLocations");

        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Obtiene una localidad determinada por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe localidad", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "La localidad no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/locations/{id}", produces = "application/json")
    public ResponseEntity<Location> getLocationById(@PathVariable long id) {

        logger.info("Inicio getLocationById");

        Location location = locationService.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        logger.info("Fin getLocationById");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /*--------------------------------FIND_HOSPITALS_BY_BEDS_RANGE_AND_SPECIALTIES----------------------------------*/
    @Operation(summary = "Obtiene los hospitales de una localidad filtrados por rango numerico de camas y por si tienen especialidades medicas o no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de hospitales",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hospital.class)))),
            @ApiResponse(responseCode = "404", description = "No hay hospitales con esas caracteristicas",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/saludaragon/locations/{id}/hospitals-beds-specialties", produces = "application/json")
    public ResponseEntity<Set<Hospital>> findHospitalsByBedsRangeAndHavingSpecialties(@PathVariable long id,
                                                            @RequestParam(value = "minBeds", defaultValue = "") int minBeds,
                                                            @RequestParam(value = "maxBeds", defaultValue = "") int maxBeds,
                                                            @RequestParam(value = "specialties", defaultValue = "") boolean specialties) {

        logger.info("Inicio findHospitalsByBedsRangeAndHavingSpecialties");

        Set<Hospital> myHospitals = locationService.findByBedsRangeAndHavingSpecialties(id, minBeds, maxBeds, specialties);

        logger.info("Fin findHospitalsByBedsRangeAndHavingSpecialties");

        return new ResponseEntity<>(myHospitals, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Registra una nueva localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra la localidad", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "Localidad no pudo ser insertada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/saludaragon/locations", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {

        logger.info("Inicio addLocation");

        Location addedLocation = locationService.addLocation(location);

        logger.info("Fin addLocation");

        return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modifica una localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la localidad", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "El localidad no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/saludaragon/locations/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Location> modifyLocation(@PathVariable long id, @RequestBody LocationDTO locationDTO) {

        logger.info("Inicio modifyLocation");

        Location location = locationService.modifyLocation(id, locationDTO);

        logger.info("Fin modifyLocation");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_BY_HAVING_HOSPITAL----------------------------------*/
    @Operation(summary = "Modifica una localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica si la localidad tiene hospital o no", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "El localidad no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/saludaragon/locations/{id}/hashospital", produces = "application/json")
    public ResponseEntity<Location> modifyLocationByHavingHospital(@PathVariable long id,
                                                                   @RequestParam(value = "hasHospital", defaultValue = "") boolean hasHospital) {

        logger.info("Inicio modifyLocationByHavingHospital");

        Location location = locationService.modifyLocationByHasHospital(id, hasHospital);

        logger.info("Fin modifyLocationByHavingHospital");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Elimina una localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la localidad", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La localidad no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/saludaragon/locations/{id}")
    public ResponseEntity<Response> deleteLocation(@PathVariable long id) {

        logger.info("Inicio deleteLocation");

        locationService.deleteLocation(id);

        logger.info("Fin deleteLocation");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(LocationNotFoundException lnfe){
        Response response = Response.errorResponse(NOT_FOUND, lnfe.getMessage());
        logger.error(lnfe.getMessage(), lnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
