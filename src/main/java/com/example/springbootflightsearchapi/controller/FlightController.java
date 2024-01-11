package com.example.springbootflightsearchapi.controller;

import com.example.springbootflightsearchapi.request.CreateFlightRequest;
import com.example.springbootflightsearchapi.response.GetFlightResponse;
import com.example.springbootflightsearchapi.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping
    public List<GetFlightResponse> getAllFlight(){
        var flights = flightService.getFlights();
        return flights.stream().map(flight -> {
            return new GetFlightResponse().builder()
                    .id(flight.getId())
                    .price(flight.getPrice())
                    .arrivalAirport(flight.getArrivalAirport())
                    .departureAirport(flight.getDepartureAirport())
                    .returnDate(flight.getReturnDate())
                    .departureDate(flight.getDepartureDate())
                    .build();
        }).toList();
    }

    @GetMapping("/{id}")
    public GetFlightResponse getFlight(@PathVariable Long id){
        var flight = flightService.getFlight(id);
        return new GetFlightResponse().builder()
                .id(flight.getId())
                .price(flight.getPrice())
                .arrivalAirport(flight.getArrivalAirport())
                .departureAirport(flight.getDepartureAirport())
                .returnDate(flight.getReturnDate())
                .departureDate(flight.getDepartureDate())
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder<?> deleteFlight(@PathVariable Long id){
        // TODO
        return ResponseEntity.noContent();
    }

    @PostMapping
    public void createFlight(@Valid @RequestBody CreateFlightRequest createFlightRequest){
        // TODO
    }
}
