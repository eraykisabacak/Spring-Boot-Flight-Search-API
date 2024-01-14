package com.example.springbootflightsearchapi.controller;

import com.example.springbootflightsearchapi.exception.AirportNotFoundException;
import com.example.springbootflightsearchapi.model.Airport;
import com.example.springbootflightsearchapi.request.CreateAirportRequest;
import com.example.springbootflightsearchapi.response.ApiResponse;
import com.example.springbootflightsearchapi.response.CreateAirportResponse;
import com.example.springbootflightsearchapi.response.GetAirportResponse;
import com.example.springbootflightsearchapi.response.ResponseHelper;
import com.example.springbootflightsearchapi.service.AirportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/airport")
@SecurityRequirement(name = "bearerAuth")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetAirportResponse>>> getAirports(){
        log.debug("[{}][getAirports] " , this.getClass().getSimpleName());
        List<Airport> airports = airportService.getAirports();
        if(airports.isEmpty()){
            throw new AirportNotFoundException("Airport not found");
        }
        log.debug("[{}][getAirports] -> response {} " , this.getClass().getSimpleName(), airports);
        List<GetAirportResponse> airportsList = airports.stream().map(airport -> {
            return new GetAirportResponse().builder().cityName(airport.getCityName()).id(airport.getId()).build();
        }).toList();

        return ResponseHelper.apiResponse(HttpStatus.OK,"Airports dataları çekildi", airportsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetAirportResponse>> getAirport(@PathVariable String id){
        log.debug("[{}][getAirport] " , this.getClass().getSimpleName());
        Airport airport = airportService.getAirport(Long.parseLong(id));
        log.debug("[{}][getAirport] -> response {} " , this.getClass().getSimpleName(), airport);
        GetAirportResponse getAirportResponse = GetAirportResponse.builder().id(airport.getId()).cityName(airport.getCityName()).build();
        return ResponseHelper.apiResponse(HttpStatus.OK,"Airport dataları çekildi", getAirportResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GetAirportResponse>> updateAirport(@PathVariable String id, @Valid @RequestBody CreateAirportRequest createAirportRequest){
        log.debug("[{}][updateAirport] " , this.getClass().getSimpleName());
        Airport airport = airportService.updateAirport(Long.parseLong(id), createAirportRequest);
        log.debug("[{}][updateAirport] -> response {} " , this.getClass().getSimpleName(), airport);
        GetAirportResponse getAirportResponse = GetAirportResponse.builder().id(airport.getId()).cityName(airport.getCityName()).build();
        return ResponseHelper.apiResponse(HttpStatus.OK,"Airport güncellendi", getAirportResponse);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateAirportResponse>> createAirport(@Valid @RequestBody CreateAirportRequest createAirportRequest){
        log.debug("[{}][createAirport] " , this.getClass().getSimpleName());
        CreateAirportResponse createAirportResponse = airportService.createAirport(createAirportRequest);
        return ResponseHelper.apiResponse(HttpStatus.CREATED,"Airport kayıt edildi", createAirportResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAirport(@PathVariable String id){
        log.debug("[{}][deleteAirport] " , this.getClass().getSimpleName());
        airportService.deleteAirport(Long.parseLong(id));
        return ResponseEntity.status(204).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
