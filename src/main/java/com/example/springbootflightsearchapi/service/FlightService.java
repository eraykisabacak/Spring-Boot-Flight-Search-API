package com.example.springbootflightsearchapi.service;

import com.example.springbootflightsearchapi.exception.FlightNotFoundException;
import com.example.springbootflightsearchapi.model.Airport;
import com.example.springbootflightsearchapi.model.Flight;
import com.example.springbootflightsearchapi.repository.FlightRepository;
import com.example.springbootflightsearchapi.request.CreateFlightRequest;
import com.example.springbootflightsearchapi.request.UpdateFlightRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private FlightRepository flightRepository;
    private AirportService airportService;

    public FlightService(FlightRepository flightRepository, AirportService airportService){
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }

    public Flight getFlight(Long id){
        return flightRepository.getFlightById(id).orElseThrow(() -> new FlightNotFoundException("Flight with ID " + id + " not found"));
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.getFlightById(id).orElseThrow(() -> new FlightNotFoundException("Flight with ID " + id + " not found"));;
        flightRepository.delete(flight);
    }

    public Flight createFlight(CreateFlightRequest createFlightRequest) {
        Airport arrivalAirport = airportService.getAirport(createFlightRequest.getArrivalAirportId());
        Airport departureAirport = airportService.getAirport(createFlightRequest.getDepartureAirportId());

        Flight flight = new Flight();
        flight.setPrice(createFlightRequest.getPrice());
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDepartureDate(createFlightRequest.getDepartureDate());
        flight.setReturnDate(createFlightRequest.getReturnDate());
        flightRepository.save(flight);
        return flight;
    }

    public Flight updateFlight(UpdateFlightRequest updateFlightRequest, Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        updateFlightFields(updateFlightRequest, flight);

        return flightRepository.save(flight);
    }

    private void updateFlightFields(UpdateFlightRequest updateFlightRequest, Flight flight) {
        if(updateFlightRequest.getArrivalAirportId() != null){
            Airport arrivalAirport = airportService.getAirport(updateFlightRequest.getArrivalAirportId());
            flight.setArrivalAirport(arrivalAirport);
        }
        if(updateFlightRequest.getArrivalAirportId() != null){
            Airport departureAirport = airportService.getAirport(updateFlightRequest.getDepartureAirportId());
            flight.setDepartureAirport(departureAirport);
        }

        Optional.ofNullable(updateFlightRequest.getPrice()).ifPresent(flight::setPrice);
        Optional.ofNullable(updateFlightRequest.getDepartureDate()).ifPresent(flight::setDepartureDate);
        Optional.ofNullable(updateFlightRequest.getReturnDate()).ifPresent(flight::setReturnDate);
    }
}
