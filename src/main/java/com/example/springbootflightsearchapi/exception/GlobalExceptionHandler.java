package com.example.springbootflightsearchapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<Object> handleAirportNotFoundException(AirportNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Object> handleFlightNotFoundException(FlightNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
