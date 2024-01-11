package com.example.springbootflightsearchapi.controller;

import com.example.springbootflightsearchapi.model.Airport;
import com.example.springbootflightsearchapi.request.CreateAirportRequest;
import com.example.springbootflightsearchapi.response.GetAirportResponse;
import com.example.springbootflightsearchapi.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public List<GetAirportResponse> getAllAirport(){
        List<Airport> airports = airportService.getAirports();
        return airports.stream().map(airport -> {
            return new GetAirportResponse().builder().cityName(airport.getCityName()).id(airport.getId()).build();
        }).toList();
    }

    @GetMapping("/{id}")
    public GetAirportResponse getAirport(@PathVariable String id){
        Airport airports = airportService.getAirport(Long.parseLong(id));
        return GetAirportResponse.builder().id(airports.getId()).cityName(airports.getCityName()).build();
    }

    @PutMapping("/{id}")
    public GetAirportResponse putAirport(@PathVariable String id, @Valid @RequestBody CreateAirportRequest createAirportRequest){
        Airport airports = airportService.updateAirport(Long.parseLong(id), createAirportRequest);
        return GetAirportResponse.builder().id(airports.getId()).cityName(airports.getCityName()).build();
    }

    @PostMapping
    public ResponseEntity<String> createAirport(@Valid @RequestBody CreateAirportRequest createAirportRequest){
        airportService.createAirport(createAirportRequest);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder<?> createAirport(@PathVariable String id){
        airportService.deleteAirport(Long.parseLong(id));
        return ResponseEntity.noContent();
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
