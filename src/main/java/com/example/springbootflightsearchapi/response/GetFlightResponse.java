package com.example.springbootflightsearchapi.response;

import com.example.springbootflightsearchapi.model.Airport;
import com.example.springbootflightsearchapi.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFlightResponse {
    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Flight returnFlight;
    private BigDecimal price;
    private LocalDate departureDate;
    private LocalDate returnDate;
}
