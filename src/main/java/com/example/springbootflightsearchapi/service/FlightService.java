package com.example.springbootflightsearchapi.service;

import com.example.springbootflightsearchapi.exception.AirportNotFoundException;
import com.example.springbootflightsearchapi.exception.FlightNotFoundException;
import com.example.springbootflightsearchapi.model.Flight;
import com.example.springbootflightsearchapi.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }

    public Flight getFlight(Long id){
        return flightRepository.getFlightById(id).orElseThrow(() -> new FlightNotFoundException("Flight with ID " + id + " not found"));
    }

}
