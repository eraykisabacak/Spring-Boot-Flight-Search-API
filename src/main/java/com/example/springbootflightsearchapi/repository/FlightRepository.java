package com.example.springbootflightsearchapi.repository;

import com.example.springbootflightsearchapi.model.Airport;
import com.example.springbootflightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Optional<Flight> getFlightById(Long id);
}
