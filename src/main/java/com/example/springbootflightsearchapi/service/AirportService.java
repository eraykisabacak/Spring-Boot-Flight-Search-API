package com.example.springbootflightsearchapi.service;

import com.example.springbootflightsearchapi.exception.AirportNotFoundException;
import com.example.springbootflightsearchapi.model.Airport;
import com.example.springbootflightsearchapi.repository.AirportRepository;
import com.example.springbootflightsearchapi.request.CreateAirportRequest;
import com.example.springbootflightsearchapi.response.CreateAirportResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirport(Long id) {
        return airportRepository.getAirportById(id).orElseThrow(() -> new AirportNotFoundException("Airport with ID " + id + " not found"));
    }

    public Airport updateAirport(Long id, CreateAirportRequest createAirportRequest) {
        var airport = airportRepository.getAirportById(id).orElseThrow(() -> new AirportNotFoundException("Airport with ID " + id + " not found"));
        airport.setCityName(createAirportRequest.getCityName());
        return airportRepository.save(airport);
    }

    public CreateAirportResponse createAirport(CreateAirportRequest createAirportRequest){
        Airport airport = new Airport();
        airport.setCityName(createAirportRequest.getCityName());
        var createdAirport = airportRepository.save(airport);

        return CreateAirportResponse.builder()
                .airport(createdAirport)
                .build();
    }

    public void deleteAirport(Long id){
       airportRepository.deleteById(id);
    }
}
