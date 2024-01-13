package com.example.springbootflightsearchapi.repository;

import com.example.springbootflightsearchapi.model.Airport;
import com.example.springbootflightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Optional<Flight> getFlightById(Long id);

    List<Flight> findAllByDepartureAirportAndArrivalAirportAndDepartureDateAndReturnDate(Airport departureAirport, Airport arrivalAirport, LocalDate departureDate, LocalDate returnDate);

    List<Flight> findAllByDepartureAirportAndArrivalAirportAndDepartureDate(Airport departureAirport, Airport arrivalAirport, LocalDate departureDate);
}
