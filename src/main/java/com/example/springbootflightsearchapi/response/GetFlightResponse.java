package com.example.springbootflightsearchapi.response;

import com.example.springbootflightsearchapi.model.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFlightResponse {
    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private BigDecimal price;
    private LocalDate departureDate;
    private LocalDate returnDate;
}
