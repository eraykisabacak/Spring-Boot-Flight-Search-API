package com.example.springbootflightsearchapi.repository;

import com.example.springbootflightsearchapi.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {
    Optional<Airport> getAirportById(Long id);
}
