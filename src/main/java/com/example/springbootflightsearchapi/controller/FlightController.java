package com.example.springbootflightsearchapi.controller;

import com.example.springbootflightsearchapi.exception.FlightNotFoundException;
import com.example.springbootflightsearchapi.model.Flight;
import com.example.springbootflightsearchapi.request.CreateFlightRequest;
import com.example.springbootflightsearchapi.request.SearchFlightRequest;
import com.example.springbootflightsearchapi.request.UpdateFlightRequest;
import com.example.springbootflightsearchapi.response.ApiResponse;
import com.example.springbootflightsearchapi.response.GetFlightResponse;
import com.example.springbootflightsearchapi.response.ResponseHelper;
import com.example.springbootflightsearchapi.service.FlightService;
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
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetFlightResponse>>> getFlights(){
        log.debug("[{}][getFlights] " , this.getClass().getSimpleName());
        var flights = flightService.getFlights();
        if(flights.isEmpty()){
            throw new FlightNotFoundException("Flight not found");
        }
        log.debug("[{}][getFlights] -> response {} " , this.getClass().getSimpleName(), flights);
        List<GetFlightResponse> flightsList = flights.stream().map(flight -> {
            return new GetFlightResponse().builder()
                    .id(flight.getId())
                    .price(flight.getPrice())
                    .arrivalAirport(flight.getArrivalAirport())
                    .departureAirport(flight.getDepartureAirport())
                    .returnDate(flight.getReturnDate())
                    .departureDate(flight.getDepartureDate())
                    .build();
        }).toList();

        return ResponseHelper.apiResponse(HttpStatus.OK,"Flights dataları çekildi",flightsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetFlightResponse>> getFlight(@PathVariable Long id){
        log.debug("[{}][getFlight] " , this.getClass().getSimpleName());
        var flight = flightService.getFlight(id);
        log.debug("[{}][getFlight] -> response {} " , this.getClass().getSimpleName(), flight);
        GetFlightResponse getFlightResponse = new GetFlightResponse().builder()
                .id(flight.getId())
                .price(flight.getPrice())
                .arrivalAirport(flight.getArrivalAirport())
                .departureAirport(flight.getDepartureAirport())
                .returnDate(flight.getReturnDate())
                .departureDate(flight.getDepartureDate())
                .returnFlight(flight.getReturnFlight())
                .build();

        return ResponseHelper.apiResponse(HttpStatus.OK,"Flight datası çekildi",getFlightResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFlight(@PathVariable Long id){
        log.debug("[{}][deleteFlight] " , this.getClass().getSimpleName());
        flightService.deleteFlight(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Flight>> createFlight(@Valid @RequestBody CreateFlightRequest createFlightRequest){
        log.debug("[{}][createFlight] " , this.getClass().getSimpleName());
        Flight flight = flightService.createFlight(createFlightRequest);
        log.debug("[{}][createFlight] -> response {} " , this.getClass().getSimpleName(), flight);
        return ResponseHelper.apiResponse(HttpStatus.CREATED,"Flight kayıt edildi", flight );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Flight>> updateFlight(@PathVariable Long id, @Valid @RequestBody UpdateFlightRequest updateFlightRequest){
        log.debug("[{}][updateFlight] " , this.getClass().getSimpleName());
        Flight flight = flightService.updateFlight(updateFlightRequest , id);
        log.debug("[{}][updateFlight] -> response {} " , this.getClass().getSimpleName(), flight);
        return ResponseHelper.apiResponse(HttpStatus.OK,"Flight güncellendi", flight );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Flight>>> search(@Valid @RequestBody SearchFlightRequest searchFlightRequest){
        List<Flight> flights = flightService.searchFlights(searchFlightRequest);
        return ResponseHelper.apiResponse(HttpStatus.OK,"Arama sonuçları", flights );
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
